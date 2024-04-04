package ma.youcode.eonboardservice.filtering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.apache.kafka.common.Uuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.eonboardservice.candidates.Candidate;
import ma.youcode.eonboardservice.candidates.CandidateRepository;
import ma.youcode.eonboardservice.joboffers.JobOffer;
import ma.youcode.eonboardservice.joboffers.JobOfferRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
// this service is just demo service with simple filter logic 
public class FilterService {
    private final Pattern emailPattern = Pattern.compile("([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})");
    private final Pattern phonePattern = Pattern.compile("([+]?[0-9]{1,4}[\\s-])?[(]?[0-9]{1,4}[)]?[-\\s\\./0-9]*");
    private final Pattern linkedInLinkPattern = Pattern.compile("www.linkedin.com/.*");
    private final SelectionFilterRepository selectionFilterRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CandidateRepository candidateRepository;

    public SelectionFilter createFilter(SelectionFilter filter) {
        // validation required
        return selectionFilterRepository.save(filter);
    }

    public List<SelectionFilter> getFilters() {
        return selectionFilterRepository.findAll();
    }

    public List<FilterReport> filterSetCandidateDataBasedOnFilters(@Valid FilterRequest filterRequest) {
        var report = new ArrayList<FilterReport>();
        filterRequest.candidateData().forEach(candidate ->{
            AtomicInteger score = new AtomicInteger(0);
            Set<String> matchedFilters = new HashSet<String>();
            Set<String> skillsFound = new HashSet<String>();
            Set<String> certifications = new HashSet<String>();
            filterRequest.filters().forEach(filter -> {
                // filter value is cama separated values I want to loop over them
                var filterValues = List.of(filter.getFilterValue().split(","));
                filterValues.forEach(filterValue -> {
                    var yExperieceNeeded = filterValue.equals("1") ? "1 year" : filterValue + " years";
                    var valueLookedFor = filterValue.trim().toLowerCase();
                    if (candidate.get("Top Skills").toLowerCase().trim().contains(valueLookedFor)) {
                        score.addAndGet(10);
                        skillsFound.add(valueLookedFor);
                        matchedFilters.add("Top Skills");
                    }else if (candidate.get("Experience").toLowerCase().trim().contains(yExperieceNeeded)){
                        score.addAndGet(10);
                        matchedFilters.add("Experience");
                    }else if (candidate.get("Summary").toLowerCase().trim().contains(valueLookedFor)){
                        score.addAndGet(10);
                        matchedFilters.add("Summary");
                    }else if (candidate.get("Certifications").toLowerCase().trim().contains(valueLookedFor)){
                        score.addAndGet(10);
                        certifications.add(valueLookedFor);
                        matchedFilters.add("Certifications");
                    }
                });
            });
            var contact = candidate.get("Contact");
            var emailMatcher = emailPattern.matcher(contact);
            var linkMatcher = linkedInLinkPattern.matcher(contact);
            var phoneMatcher = phonePattern.matcher(contact);
            var email = emailMatcher.find() ? emailMatcher.group() : "N/A";
            var linkedIn = linkMatcher.find() ? linkMatcher.group() : "N/A";
            var phone = phoneMatcher.find() ? phoneMatcher.group() : "N/A";
            var Matchedskills = skillsFound.stream().reduce("", (a,b) -> a + ", " + b);
            var MatchedCertifications = certifications.stream().reduce("", (a,b) -> a + ", " + b);
            var filterReport = FilterReport.builder()
                    .id(candidate.get("Candidate ID"))
                    .resume(candidate.get("resumeId"))
                    .score(score.get())
                    .email(email)
                    .phoneNumber(phone)
                    .linkedIn(linkedIn)
                    .matchedFilters(matchedFilters)
                    .certifications(MatchedCertifications)
                    .skills(Matchedskills)
                    .build();
            var nameSplit = candidate.get("name").split(" ");
            var firstName = nameSplit[0];
            String lastName = "";
            for(int i=1; i<nameSplit.length;i++){
                lastName+= nameSplit[i];
            }
            Candidate candidateEntity = Candidate.builder()
                    .email(email)
                    .certifications(MatchedCertifications)
                    .lastName(lastName)
                    .firstName(firstName)
                    .linkedIn(linkedIn)
                    .phoneNumber(phone)
                    .skills(Matchedskills)
                    .score(score.get())
                    .candidateId(UUID.fromString(candidate.get("Candidate ID")))
                    .build();
            candidateRepository.save(candidateEntity);
            report.add(filterReport);
        });
        if (filterRequest.saveJobOffer()  && filterRequest.jobDescription() != null) {
            // extract data from filters
            JobOffer jobOffer = new JobOffer();
            filterRequest.filters().forEach(f -> {
                if(f.getFilterKey().equals("title")){
                    jobOffer.setJobTitle(f.getFilterValue());
                }else if(f.getFilterKey().equals("location")){
                    jobOffer.setJobLocation(f.getFilterValue());
                }else if(f.getFilterKey().equals("experience")){
                    jobOffer.setJobExperience(f.getFilterValue());
                }else if(f.getFilterKey().equals("skills")){
                    jobOffer.setJobSkills(f.getFilterValue());
                }

            });
            jobOffer.setJobDescription(filterRequest.jobDescription());
            jobOfferRepository.save(jobOffer);
        }
        return report.stream()
                .sorted(Comparator.comparing(FilterReport::score).reversed())
                .toList();
    }
    
}
