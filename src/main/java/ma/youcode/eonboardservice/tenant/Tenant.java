package ma.youcode.eonboardservice.tenant;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tenants")
@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;
    @Column(name = "requestor_role")
    private String requestorRole;

    @Column(name = "domain_name")
    private String domainName;
    @Column(name = "requestor_professional_email")
    private String requestorProfessionalEmail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tenant")
    private List<User> users = new ArrayList<>();


    public void addUser(User user) {
        if(users==null) users=new ArrayList<>();
        users.add(user);
    }
}
