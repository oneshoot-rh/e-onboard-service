package ma.youcode.eonboardservice.Employees;

import lombok.Builder;

@Builder
public record EmployeeDto(
    String name,
    String code,
    String role
) {
} 