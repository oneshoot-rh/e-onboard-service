package ma.youcode.eonboardservice.filtering;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.filter.Filter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
// this service is just demo service with simple filter logic 
public class FilterService {

    private final SelectionFilterRepository selectionFilterRepository;

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
            BigInteger score = BigInteger.ZERO;
            List<String> matchedFilters = new ArrayList<>();
            filterRequest.filters().forEach(filter -> {
                if(candidate.get(filter.getFilterKey()).contains(filter.getFilterValue())){
                    score.add(BigInteger.TEN);
                    matchedFilters.add(filter.getFilterKey());
                }else if (candidate.get("Experience").contains(filter.getFilterValue())){
                    score.add(BigInteger.TEN);
                    matchedFilters.add(filter.getFilterKey());
                }else if (candidate.get("Summary").contains(filter.getFilterValue())){
                    score.add(BigInteger.TEN);
                    matchedFilters.add(filter.getFilterKey());
                }
            });
            var filterReport = FilterReport.builder()
                    .candidateId(candidate.get("Candidate ID"))
                    .resumeId(candidate.get("resumeId"))
                    .score(score.intValue())
                    .matchedFilters(matchedFilters)
                    .build();
            report.add(filterReport);
        });
        return report;
    }
    
}
