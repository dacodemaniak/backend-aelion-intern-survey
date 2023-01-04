package survey.backend.repository.play;

// THIS IS NOT A TEST

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import survey.backend.repository.PoeRepository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)  // use app db
public class PoeWithTraineesPlay {

    @Autowired
    PoeRepository poeRepository;

    @Test
    void readPoeWithTrainees() {
        var poe = poeRepository.findById(1L).get();
        // select poe id = 1 (without its trainees)

        System.out.println(poe);

        System.out.println(poe.getTrainees());
        // select trainees of poe #1
    }
}
