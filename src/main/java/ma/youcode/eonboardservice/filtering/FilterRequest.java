package ma.youcode.eonboardservice.filtering;

import java.util.HashMap;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record FilterRequest(
    @NotEmpty List<SelectionFilter> filters, 
    @NotEmpty List<HashMap<String,String>> candidateData,
    String jobDescription,
    boolean useBothFilter,
    boolean saveJobOffer 
) { 
    
}
