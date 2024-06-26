package ma.youcode.eonboardservice.utils.handlers;

import ma.youcode.eonboardservice.utils.exceptions.TenantNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<Object> handleTenantNotFoundException(TenantNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    record ErrorResponse(String message) {
    }
}
