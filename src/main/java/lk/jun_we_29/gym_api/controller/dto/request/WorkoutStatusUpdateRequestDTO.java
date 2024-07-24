package lk.jun_we_29.gym_api.controller.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutStatusUpdateRequestDTO {

    private Long id;

    private Double distance;

    private Integer pushupCount;

    private Double weightLifted;

    private String description;

    private LocalDateTime updatedAt;


}
