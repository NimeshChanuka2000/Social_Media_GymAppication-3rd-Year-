package lk.jun_we_29.gym_api.service.impl;

import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusUpdateRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusTemplateDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusUpdateDeleteResponseDTO;
import lk.jun_we_29.gym_api.exception.UserNotFoundException;
import lk.jun_we_29.gym_api.exception.WorkoutStatusNotFoundException;
import lk.jun_we_29.gym_api.model.CurrentWorkoutStatus;
import lk.jun_we_29.gym_api.model.User;
import lk.jun_we_29.gym_api.repository.UserRepository;
import lk.jun_we_29.gym_api.repository.WorkoutStatusRepository;
import lk.jun_we_29.gym_api.service.WorkStatusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkStatusServiceImpl implements WorkStatusService {

    private final WorkoutStatusRepository workoutStatusRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createWorkoutStatus( WorkoutStatusRequestDTO workoutStatusRequestDTO)throws UserNotFoundException {

        CurrentWorkoutStatus currentWorkoutStatus = new CurrentWorkoutStatus();
        currentWorkoutStatus.setDistance(workoutStatusRequestDTO.getDistance());
        currentWorkoutStatus.setPushupCount(workoutStatusRequestDTO.getPushupCount());
        currentWorkoutStatus.setWeightLifted(workoutStatusRequestDTO.getWeightLifted());
        currentWorkoutStatus.setDescription(workoutStatusRequestDTO.getDescription());
        workoutStatusRepository.save(currentWorkoutStatus);
    }


    @Override
    public List<WorkoutStatusTemplateDTO> getAllStatus() {

        List<CurrentWorkoutStatus> statusList = workoutStatusRepository.findAll();

        return statusList.stream()
                .map(statusLists -> modelMapper.map(statusLists, WorkoutStatusTemplateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutStatusUpdateDeleteResponseDTO updateWorkoutStatus(Long id, WorkoutStatusUpdateRequestDTO workoutStatusUpdateRequestDTO) throws WorkoutStatusNotFoundException {
        Optional<CurrentWorkoutStatus> exStatus = workoutStatusRepository.findById(id);
        CurrentWorkoutStatus currentWorkoutStatus = exStatus.orElseThrow(
                () -> new WorkoutStatusNotFoundException("Workout Status not found this id:" + id)
        );

        CurrentWorkoutStatus updateStatus = exStatus.get();
        updateStatus.setDistance(workoutStatusUpdateRequestDTO.getDistance());
        updateStatus.setPushupCount(workoutStatusUpdateRequestDTO.getPushupCount());
        updateStatus.setWeightLifted(workoutStatusUpdateRequestDTO.getWeightLifted());
        updateStatus.setDescription(workoutStatusUpdateRequestDTO.getDescription());
        workoutStatusRepository.save(updateStatus);

        return new WorkoutStatusUpdateDeleteResponseDTO("Status Update Successful");
    }

    @Override
    public WorkoutStatusUpdateDeleteResponseDTO deleteStatus(Long id) throws WorkoutStatusNotFoundException {

        Optional<CurrentWorkoutStatus> optionalCurrentWorkoutStatus = workoutStatusRepository.findById(id);
        CurrentWorkoutStatus currentWorkoutStatus = optionalCurrentWorkoutStatus.orElseThrow(
                () -> new WorkoutStatusNotFoundException("Workout Status not found this id:" + id)
        );

        workoutStatusRepository.deleteById(id);
        return new WorkoutStatusUpdateDeleteResponseDTO("Status delete Successful");
    }

}
