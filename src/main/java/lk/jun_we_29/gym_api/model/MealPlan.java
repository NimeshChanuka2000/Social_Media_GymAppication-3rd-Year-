package lk.jun_we_29.gym_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "mealPlan")
@Data
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealName;

    private String planDescription;

    private Date time;

    @ManyToOne
    private User user;
}
