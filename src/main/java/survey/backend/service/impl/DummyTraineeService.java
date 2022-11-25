package survey.backend.service.impl;

import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;

@Service
public class DummyTraineeService implements TraineeService {

    private static final RandomGenerator RANDOM_ID_GENERATOR = RandomGenerator.getDefault();

    @Override
    public Set<TraineeDto> findAll() {
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastname("Doe")
                        .firstname("John")
                        .birthdate(LocalDate.of(1900, 1, 12))
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastname("Doe")
                        .firstname("Jane")
                        .birthdate(LocalDate.of(1920, 7, 6))
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastname("Unknown")
                        .firstname("John")
                        .birthdate(LocalDate.of(1952, 2, 29))
                        .build(),
                TraineeDto.builder()
                        .id(78)
                        .lastname("Unknown")
                        .firstname("Jane")
                        .birthdate(LocalDate.of(1942, 1, 29))
                        .build()
        );
    }

    @Override
    public Optional<TraineeDto> findById(int id) {
        if (id % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(TraineeDto.builder()
                    .id(id)
                    .lastname("Bond")
                    .firstname("James")
                    .birthdate(LocalDate.of(1900, 10, 7))
                    .build());
        }
    }

    @Override
    public Set<TraineeDto> search(String lastname, String firstname) {
        final String DEFAULT_FAMILY = "found";
        String dummyLlastname = Objects.isNull(lastname) ? DEFAULT_FAMILY : lastname;
        if (Objects.isNull(lastname) && Objects.isNull(firstname)){
            return Set.of();
        }
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastname(dummyLlastname)
                        .firstname(Objects.isNull(firstname) ? "John" : firstname)
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastname(dummyLlastname)
                        .firstname(Objects.isNull(firstname) ? "Jane" : firstname)
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastname(dummyLlastname)
                        .firstname(Objects.isNull(firstname) ? "Jimmy" : firstname)
                        .build()
        );
    }

    @Override
    public TraineeDto add(TraineeDto traineeDto) {
        traineeDto.setId(RANDOM_ID_GENERATOR.nextInt());
        return traineeDto;
    }

    @Override
    public Optional<TraineeDto> update(TraineeDto traineeDto) {
        if (traineeDto.getId() % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(traineeDto);
        }
    }

    @Override
    public boolean delete(int id) {
        return  id % 2 == 1;
    }
}
