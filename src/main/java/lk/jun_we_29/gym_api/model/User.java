package lk.jun_we_29.gym_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private RegistrationSource source;

    private String profilePictureUrl;

    private String bio;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> like = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CurrentWorkoutStatus> currentWorkoutStatuses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MealPlan> mealPlans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WorkOutPlan> workoutPlans = new ArrayList<>();
}
