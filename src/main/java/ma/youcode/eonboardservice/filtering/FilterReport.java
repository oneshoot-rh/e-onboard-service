package ma.youcode.eonboardservice.filtering;

import java.util.List;

import lombok.Builder;

@Builder
public record FilterReport(
    String candidateId,
    String resumeId,
    float score,
    List<String> matchedFilters
) {
    
}
