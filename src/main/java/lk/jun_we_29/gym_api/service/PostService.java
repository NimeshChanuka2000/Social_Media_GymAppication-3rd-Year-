package lk.jun_we_29.gym_api.service;

import org.springframework.web.multipart.MultipartFile;
import lk.jun_we_29.gym_api.controller.dto.request.CreatePostRequestDTO;
import lk.jun_we_29.gym_api.controller.dto.response.CreatePostResponseDTO;
import lk.jun_we_29.gym_api.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Long id,String content,String mediaType,MultipartFile[] files)throws Exception;

    List<CreatePostResponseDTO> getAllPost(Long id)throws Exception;
}
