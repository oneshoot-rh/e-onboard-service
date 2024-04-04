package ma.youcode.eonboardservice.candidates;

import java.util.List;
import java.util.UUID;

public record SelectedCandidatesDto(
    List<UUID> selectedCandidateIds
) {
}
