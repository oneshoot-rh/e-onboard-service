package ma.youcode.eonboardservice.filtering;

import java.util.UUID;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.youcode.eonboardservice.utils.UUIDToStringConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "selection_filters")
@Entity
public class SelectionFilter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filterKey;
    private String filterValue;
    @Convert(converter = UUIDToStringConverter.class)
    private UUID processID;
}
