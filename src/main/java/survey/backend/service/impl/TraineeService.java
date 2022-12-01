package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.TraineeRepository;

import java.util.Optional;



@Service
public class TraineeService implements survey.backend.service.TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Override
    public Iterable<Trainee> findAll() {
        return this.traineeRepository.findAll();
    }

    @Override
    public Optional<Trainee> findById(int id) {
        return this.traineeRepository.findById((long) id);
    }

    @Override
    public Iterable<Trainee> search(String lastname, String firstname) {
        // @Todo implement method in the repository
        return null;
    }

    @Override
    public Trainee add(TraineeDto traineeDto) {
        return this.traineeRepository.save(traineeDto.toTrainee());
    }

    @Override
    public Optional<Trainee> update(TraineeDto traineeDto) {
        Trainee trainee = traineeDto.toTrainee();
        Optional<Trainee> oTrainee = this.traineeRepository.findById(trainee.getId());
        if (oTrainee.isPresent()) {
            this.traineeRepository.save(trainee);
            return Optional.of(trainee);
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int id) {
       // 1st Get the Trainee by its id
        Optional<Trainee> oTrainee = this.traineeRepository.findById((long) id);
        if (oTrainee.isPresent()) {
            this.traineeRepository.delete(oTrainee.get());
            return true;
        }
        return false;
    }
}
