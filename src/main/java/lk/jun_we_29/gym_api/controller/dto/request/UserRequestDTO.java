package lk.jun_we_29.gym_api.controller.dto.request;

import lombok.Data;

@Data
public class UserRequestDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String profilePictureUrl;

    private String bio;
}
