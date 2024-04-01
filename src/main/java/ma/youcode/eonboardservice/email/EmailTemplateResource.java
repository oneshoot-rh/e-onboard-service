package ma.youcode.eonboardservice.email;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cl/email-templates")
@RequiredArgsConstructor
public class EmailTemplateResource {

    private final EmailTemplateRepository emailTemplateRepository;

    @GetMapping
    public ResponseEntity<List<EmailTemplate>> getAllEmailTemplates() {
        return  ResponseEntity.ok(emailTemplateRepository.findAll());
    }

    
}
