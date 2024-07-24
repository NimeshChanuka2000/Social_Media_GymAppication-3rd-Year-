package lk.jun_we_29.gym_api.controller;

import lk.jun_we_29.gym_api.controller.dto.response.CreatePostResponseDTO;
import lk.jun_we_29.gym_api.exception.UserNotFoundException;
import lk.jun_we_29.gym_api.model.Post;
import lombok.AllArgsConstructor;
import lk.jun_we_29.gym_api.controller.dto.request.CreatePostRequestDTO;
import lk.jun_we_29.gym_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @CrossOrigin(origins = "*")
    @PostMapping("/users/{user-id}/posts")
    public Post addPost(@PathVariable("user-id") Long userId,
                        @RequestParam("content") String content,
                        @RequestParam("media_type") String mediaType,
                        @RequestParam("files") MultipartFile[] files) throws Exception {


        return postService.createPost(userId, content, mediaType, files);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}/posts")
    public ResponseEntity<?> getAllPosts(@PathVariable("id") Long id) {
        try {
            List<CreatePostResponseDTO> responseList = postService.getAllPost(id);
            return ResponseEntity.ok(responseList);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }






}
