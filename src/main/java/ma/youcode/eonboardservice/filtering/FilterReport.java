package ma.youcode.eonboardservice.filtering;

import java.util.Set;

import lombok.Builder;

@Builder
public record FilterReport(
    String id,
    String email,
    String name,
    String phoneNumber,
    String skills,
    String experience,
    String linkedIn,
    String education,
    String certifications,
    String resume,
    int score,
    Set<String> matchedFilters
) {
    
}
