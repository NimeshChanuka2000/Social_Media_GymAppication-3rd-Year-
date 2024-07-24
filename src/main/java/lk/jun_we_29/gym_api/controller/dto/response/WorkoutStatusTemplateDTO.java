package lk.jun_we_29.gym_api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutStatusTemplateDTO {

    private Long id;
    private Double distance;
    private Integer pushupCount;
    private Double weightLifted;
    private String statusId;
    private String description;
    private LocalDateTime createdAt;
}
