package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {

    // Native SQL if no other possibility
    @Query(
            value = "SELECT id, last_name, first_name, birth_date, phone_number, email "
            + " FROM trainee "
            + " WHERE UPPER(last_name) = UPPER(:lastName) "
            + " AND UPPER(first_name) = UPPER(:firstName)",
            nativeQuery = true
    )
    List<Trainee> listByLastNameAndFirstName(@Param(value="lastName") String lastName, @Param(value="firstName") String firstName);

    // JPQL/HQL
    @Query(
            value="SELECT t FROM Trainee t "
            +" WHERE UPPER(t.lastName) = UPPER(:lastName) "
            +" AND UPPER(t.firstName) = UPPER(:firstName)"
    )
    List<Trainee> byLastNameAndFirstName(@Param(value="lastName") String lastName, @Param(value="firstName") String firstName);

    // JPQL auto generated according to Spring Data Jpa vocabulary
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Trainee> findByLastNameIgnoreCase(String lastName);

    List<Trainee> findByFirstNameIgnoreCase(String firstName);

    Optional<Trainee> findByEmail(String email);

}
