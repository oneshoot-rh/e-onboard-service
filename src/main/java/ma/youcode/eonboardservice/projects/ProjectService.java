package ma.youcode.eonboardservice.projects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.youcode.eonboardservice.Employees.EmployeeDto;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AssignationRepository assignationRepository;


    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    

    public List<ProjectAssignation> getAllProjectAssignations(){
        return  projectRepository.findAll().stream().map(p ->{
            var assignations = assignationRepository.findAllByProjectId(p.getId());
            var assignationEmployees = assignations.stream()
                                                    .map(as -> {
                                                        return EmployeeDto.builder()
                                                        .name(as.getEmployee().getName())
                                                        .role(as.getEmployee().getRole())
                                                        .code(as.getEmployee().getEmployeeCode())
                                                        .build();
                                                    }).toList();

            return ProjectAssignation.builder()
                                                 .deployedEmployees(assignationEmployees)
                                                 .project(p.getName())
                                                 .projectManager(p.getProjectManagerWithCode())
                                                 .projectFamily(p.getFamily().getName())
                                                 .status(p.getStatus().toString())
                                                 .build();
        }).toList();
    }
}
