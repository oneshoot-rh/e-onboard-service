package ma.youcode.eonboardservice.projects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import ma.youcode.eonboardservice.Employees.EmployeeDto;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProjectAssignation(
    List<EmployeeDto> deployedEmployees,
    String status,
    String project,
    String projectFamily,
    String projectManager
) {
} 