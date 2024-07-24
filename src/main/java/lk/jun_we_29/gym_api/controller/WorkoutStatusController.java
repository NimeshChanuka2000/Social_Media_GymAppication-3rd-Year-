package lk.jun_we_29.gym_api.controller;

import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.request.WorkoutStatusUpdateRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusTemplateDTO;
import lk.jun_we_29.gym_api.controller.dto.response.WorkoutStatusUpdateDeleteResponseDTO;
import lk.jun_we_29.gym_api.exception.UserNotFoundException;
import lk.jun_we_29.gym_api.exception.WorkoutStatusNotFoundException;
import lk.jun_we_29.gym_api.service.WorkStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout-status")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class WorkoutStatusController {

    private final WorkStatusService workStatusService;

    @PostMapping("/create")
    public void createWorkoutStatus(@RequestBody WorkoutStatusRequestDTO workoutStatusRequestDTO)throws UserNotFoundException {

        workStatusService.createWorkoutStatus(workoutStatusRequestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutStatusTemplateDTO>> getAllStatus(){
        List<WorkoutStatusTemplateDTO> templateDTOList = workStatusService.getAllStatus();
        return ResponseEntity.ok(templateDTOList);
    }


    @PutMapping("/{id}/update")
    public WorkoutStatusUpdateDeleteResponseDTO updateWorkoutStatus(@PathVariable Long id, @RequestBody WorkoutStatusUpdateRequestDTO workoutStatusUpdateRequestDTO)throws WorkoutStatusNotFoundException {
        return workStatusService.updateWorkoutStatus(id, workoutStatusUpdateRequestDTO);
    }


    @DeleteMapping("/{id}/delete")
    public WorkoutStatusUpdateDeleteResponseDTO deleteStatus(@PathVariable Long id) throws WorkoutStatusNotFoundException {
       return workStatusService.deleteStatus(id);
    }
}
