package lk.jun_we_29.gym_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "workoutPlan")
@Data
public class WorkOutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    private String planDescription;

    private Date time;

    @ManyToOne
    private User user;


}
