package lk.jun_we_29.gym_api.controller.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutStatusRequestDTO {

    private Long id;

    private Double distance;

    private Integer pushupCount;

    private Double weightLifted;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
