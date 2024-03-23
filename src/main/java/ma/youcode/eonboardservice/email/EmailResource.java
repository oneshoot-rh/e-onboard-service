package ma.youcode.eonboardservice.email;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor    
public class EmailResource {



    private final EmailService emailService;



    @PostMapping("/email-templates")
    public ResponseEntity<EmailTemplate> createEmailTemplate(EmailTemplate emailTemplate) {
        return ResponseEntity.ok(emailService.createEmailTemplate(emailTemplate));
    }

    @GetMapping("/email-templates")
    public ResponseEntity<List<EmailTemplate>> getAllEmailTemplates() {
        return ResponseEntity.ok(emailService.getAllEmailTemplates());
    }


    @PostMapping("/email-settings")
    public ResponseEntity<EmailSettings> createEmailSetting(EmailSettings emailSettings) {
        return ResponseEntity.ok(emailService.createEmailSettings(emailSettings));
    }

    @PostMapping("/email-settings")
    public ResponseEntity<List<EmailSettings>> getAllEmailSettings() {
        return ResponseEntity.ok(emailService.getAllEmailSettings());
    }

    
}
