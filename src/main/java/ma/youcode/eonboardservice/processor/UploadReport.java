package ma.youcode.eonboardservice.processor;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
public record UploadReport(
        List<UploadFileState> uploadedFiles,
        LocalDateTime uploadDate,
        UUID processID,
        String uploadDirectory,
        List<Map<String, String>> candidateData
) {
}
