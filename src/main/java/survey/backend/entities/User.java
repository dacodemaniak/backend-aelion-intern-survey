package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String userLogin;

    @Column(nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roles = new HashSet<UserRole>();
}
