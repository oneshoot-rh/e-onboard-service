package ma.youcode.eonboardservice.joboffers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/job-offers")
@RequiredArgsConstructor
public class JobOfferResource {
    

    private final JobOfferService jobOfferService;


    @GetMapping
    public ResponseEntity<List<JobOffer>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferService.getAllJobOffers());
    }

    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(JobOffer jobOffer) {
        return ResponseEntity.ok(jobOfferService.createJobOffer(jobOffer));
    }
}
