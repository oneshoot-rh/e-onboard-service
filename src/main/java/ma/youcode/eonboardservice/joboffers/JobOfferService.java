package ma.youcode.eonboardservice.joboffers;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobOfferService {
    
    private final JobOfferRepository jobOfferRepository;


    public JobOffer createJobOffer(JobOffer jobOffer) {
        jobOffer.setId(null);
        return jobOfferRepository.save(jobOffer);
    }


    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    
    
}
