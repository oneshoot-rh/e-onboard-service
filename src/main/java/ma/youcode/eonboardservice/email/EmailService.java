package ma.youcode.eonboardservice.email;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailSettingsRepository emailSettingsRepository;

    public EmailTemplate createEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplate.setId(null);
        return emailTemplateRepository.save(emailTemplate);
    }

    public EmailSettings createEmailSettings(EmailSettings emailSetting) {
        emailSetting.setId(null);
        return emailSettingsRepository.save(emailSetting);
    }

    public List<EmailTemplate> getAllEmailTemplates() {
        return emailTemplateRepository.findAll();
    }
    

    public List<EmailSettings> getAllEmailSettings() {
        return emailSettingsRepository.findAll();
    }
}
