package ma.youcode.eonboardservice.Employees;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cl/employees")
@RequiredArgsConstructor
public class EmployeeResource {

    private final EmployeeService employeeService;



    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }
    
}
