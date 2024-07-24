package lk.jun_we_29.gym_api.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDTO {
    private String content;
    private String media_type;
    private String media_url;
}
