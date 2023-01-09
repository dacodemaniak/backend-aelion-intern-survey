package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.repository.PoeRepository;
import survey.backend.entities.Poe;
import survey.backend.repository.TraineeRepository;
import survey.backend.service.PoeService;
import survey.backend.utils.StreamUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class PoeServiceImplJpa implements PoeService {

    @Autowired
    PoeRepository poeRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    ModelMapper modelMapper;

    public Collection<PoeDto> findAll() {
        return StreamUtils.toStream(poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeDto.class))
                .toList();
    }

    @Override
    public Optional<PoeFullDto> findById(long id) {
        return poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeFullDto.class));
    }

    @Override
    public PoeDto add(PoeDto poeDto) {
        Poe poeEntity = modelMapper.map(poeDto, Poe.class);
        poeRepository.save(poeEntity);
        return modelMapper.map(poeEntity, PoeDto.class);
    }

    @Override
    public Optional<PoeFullDto> update(PoeDto poeDto) {
        return poeRepository.findById(poeDto.getId())
                .map(poeEntity -> {
                    modelMapper.map(poeDto, poeEntity);
                    poeRepository.save(poeEntity);
                    return modelMapper.map(poeEntity, PoeFullDto.class);
                });
    }


    @Override
    public Optional<PoeFullDto> addTrainee(long poeId, long traineeId) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> traineeRepository.findById(traineeId)
                        .map(traineeEntity -> {
                            // add trainee to poe
                            poeEntity.getTrainees().add(traineeEntity);
                            // sync with DB
                            poeRepository.save(poeEntity);
                            // return poe updated
                            return modelMapper.map(poeEntity, PoeFullDto.class);
                        })
                );
    }

    @Override
    public Optional<PoeFullDto> addTrainees(long poeId, Collection<Long> traineeIds) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    var traineeEntities = StreamUtils.toStream(traineeRepository.findAllById(traineeIds))
                            .toList();
                    if (traineeIds.size() != traineeEntities.size()) {
                        return Optional.empty();
                    }
                    // add trainees in poe
                    poeEntity.getTrainees().addAll(traineeEntities);
                    // sync with DB
                    poeRepository.save(poeEntity);
                    // return poe updated as DTO
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public Optional<PoeFullDto> removeTrainee(long poeId, long traineeId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<PoeFullDto> clearTrainees(long poeId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public boolean remove(long poeId){
        return poeRepository.findById(poeId)
                .map(poeEntity -> {
                    poeRepository.delete(poeEntity);
                    return true;
                })
                .orElse(false);
    }

}
