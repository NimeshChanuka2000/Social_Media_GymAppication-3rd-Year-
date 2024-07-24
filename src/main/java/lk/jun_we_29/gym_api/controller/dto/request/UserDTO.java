package lk.jun_we_29.gym_api.controller.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String profileImage;
    private String mobileNumber;
    private String source;

}

