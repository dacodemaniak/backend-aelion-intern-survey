package survey.backend.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import survey.backend.entities.Poe;
import survey.backend.enums.PoeType;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ActiveProfiles("testu")
@AutoConfigureTestDatabase(replace = NONE) // Do not use H2 but DB configured
class PoeRepositoryTest {

    @Autowired
    PoeRepository poeRepository;

    // Hibernate component to write data in DB before each test
    @Autowired
    TestEntityManager entityManager;

    @SneakyThrows
    @Test
    void testFindByEndingInRange(){
        // given
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df.parse("2022-01-01");
        Date date2 = df.parse("2022-12-31");
        var poes = List.of(
                Poe.builder()
                        .title("Java Fullstack 1")
                        .beginDate(df.parse("2021-01-01"))
                        .endDate(df.parse("2021-06-01"))
                        .type(PoeType.POEI)
                        .build(),
                Poe.builder()
                        .title("Java Fullstack 2")
                        .beginDate(df.parse("2021-10-01"))
                        .endDate(date1)
                        .type(PoeType.POEI)
                        .build(),
                Poe.builder()
                        .title("Java Fullstack 3")
                        .beginDate(df.parse("2022-03-01"))
                        .endDate(df.parse("2022-06-01"))
                        .type(PoeType.POEI)
                        .build(),
                Poe.builder()
                        .title("Java Fullstack 4")
                        .beginDate(df.parse("2022-10-01"))
                        .endDate(date2)
                        .type(PoeType.POEI)
                        .build(),
                Poe.builder()
                        .title("Java Fullstack 5")
                        .beginDate(df.parse("2022-10-01"))
                        .endDate(df.parse("2023-01-01"))
                        .type(PoeType.POEI)
                        .build()
        );
        // save data in DB
        poes.forEach(poe -> entityManager.persist(poe));
        entityManager.flush(); // synchronize hibernate cache with DB
        // empty hibernate cache
        entityManager.clear();

        // when
        var poesFound = poeRepository.findByEndingInRange(date1, date2);

        // then
        assertEquals(3, poesFound.size(), "poe found number");
        assertAll(poesFound.stream()
                .map(poe -> () -> assertTrue(
                        (date1.compareTo(poe.getEndDate()) <= 0)
                        && (poe.getEndDate().compareTo(date2) <= 0),
                        "poe end date in range"
                ))
        );
    }

}