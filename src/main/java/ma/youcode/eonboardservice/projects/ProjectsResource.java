package ma.youcode.eonboardservice.projects;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cl/projects")
public class ProjectsResource {

    private final ProjectService projectService ;

    @GetMapping("/assignations")
    public ResponseEntity<List<ProjectAssignation>> getAllProjectAssignations(){
        return ResponseEntity.ok(projectService.getAllProjectAssignations());
    }
    
}
