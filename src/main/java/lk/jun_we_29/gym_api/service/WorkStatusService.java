package lk.jun_we_29.gym_api.service;

import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusUpdateRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusTemplateDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusUpdateDeleteResponseDTO;
import lk.jun_we_29.gym_api.exception.UserNotFoundException;
import lk.jun_we_29.gym_api.exception.WorkoutStatusNotFoundException;

import java.util.List;

public interface WorkStatusService {

    void createWorkoutStatus(WorkoutStatusRequestDTO workoutStatusRequestDTO)throws UserNotFoundException;

    List<WorkoutStatusTemplateDTO> getAllStatus();

    WorkoutStatusUpdateDeleteResponseDTO updateWorkoutStatus(Long id, WorkoutStatusUpdateRequestDTO workoutStatusUpdateRequestDTO) throws WorkoutStatusNotFoundException;

    WorkoutStatusUpdateDeleteResponseDTO deleteStatus(Long id) throws WorkoutStatusNotFoundException;

}
