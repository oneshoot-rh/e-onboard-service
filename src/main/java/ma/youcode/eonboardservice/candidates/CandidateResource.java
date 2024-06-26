package ma.youcode.eonboardservice.candidates;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cl/candidates")
@RequiredArgsConstructor
public class CandidateResource {

    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<Page<Candidate>> getAllCandidates(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "false") Boolean onlySelected
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(candidateService.getAllCandidates(pageable,onlySelected));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/select")
    public ResponseEntity<List<Candidate>> selectCandidateForAJob(
        @RequestBody SelectedCandidatesDto selected
    ){
        return ResponseEntity.ok(candidateService.makeCandidatesSelected(selected));
    }
}
