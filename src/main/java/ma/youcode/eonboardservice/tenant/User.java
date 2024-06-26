package ma.youcode.eonboardservice.tenant;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String name;
    String password;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    Tenant tenant;
}
