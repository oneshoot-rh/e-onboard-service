package ma.youcode.eonboardservice.filtering;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/filtering")
@RequiredArgsConstructor
public class FilterResource {

    private final FilterService filterService;


    @PostMapping
    public ResponseEntity<?> filterCandidates(
        @Valid @RequestBody FilterRequest filterRequest) {
        return ResponseEntity.ok(filterService.filterSetCandidateDataBasedOnFilters(filterRequest));
    }
    
    
}
