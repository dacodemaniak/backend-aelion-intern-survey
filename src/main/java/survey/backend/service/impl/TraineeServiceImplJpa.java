package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.repository.TraineeRepository;
import survey.backend.service.TraineeService;
import survey.backend.utils.StreamUtils;

import java.util.Collection;
import java.util.Optional;



@Service
public class TraineeServiceImplJpa implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<TraineeDto> findAll() {

        return StreamUtils.toStream(this.traineeRepository.findAll())
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                .toList();
    }

    @Override
    public Optional<TraineeDto> findById(long id) {
        return this.traineeRepository.findById(id)
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class));
    }

    @Override
    public Collection<TraineeDto> search(String lastname, String firstname) {
        if (lastname != null && firstname != null) {
            return this.traineeRepository.listByLastNameAndFirstName(lastname, firstname)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
        }

        if (lastname != null) {
            return this.traineeRepository.findByLastName(lastname)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
        }

        return this.traineeRepository.findByFirstName(firstname)
                .stream()
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                .toList();
    }

    @Override
    public TraineeDto add(TraineeDto traineeDto) {
        Trainee traineeEntity = modelMapper.map(traineeDto, Trainee.class);
        this.traineeRepository.save(traineeEntity); // SQL: insert + id
        TraineeDto traineeDtoResponse = modelMapper.map(traineeEntity, TraineeDto.class);
        return traineeDtoResponse;
    }

    @Override
    public Optional<TraineeDto> update(TraineeDto traineeDto) {
        return this.traineeRepository.findById(traineeDto.getId())
                .map(traineeEntity -> {
                    // update entity object read from db with dto fields
                    modelMapper.map(traineeDto, traineeEntity);
                    // synchronize with database
                    traineeRepository.save(traineeEntity); // SQL: update
                    // transform entity updated in dto
                    return modelMapper.map(traineeEntity, TraineeDto.class);
                });
    }

    @Override
    public boolean remove(long id) {
       // 1st Get the Trainee by its id
        Optional<Trainee> oTrainee = this.traineeRepository.findById(id);
        if (oTrainee.isPresent()) {
            this.traineeRepository.delete(oTrainee.get());
            return true;
        }
        return false;
    }

}
