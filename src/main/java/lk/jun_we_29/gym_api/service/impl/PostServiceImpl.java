package lk.jun_we_29.gym_api.service.impl;

import lk.jun_we_29.gym_api.controller.dto.response.CreatePostResponseDTO;
import lk.jun_we_29.gym_api.exception.UserNotFoundException;
import lk.jun_we_29.gym_api.model.Post;
import lk.jun_we_29.gym_api.model.User;
import lk.jun_we_29.gym_api.repository.PostRepository;
import lk.jun_we_29.gym_api.repository.UserRepository;
import lk.jun_we_29.gym_api.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public Post createPost(Long id, String content, String mediaType, MultipartFile[] files) throws Exception {

        String uploadDir = "PostImage";
        List<String> fileUrls = new ArrayList<>();


        for (MultipartFile file : files) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            fileUrls.add(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, file);
                String fileUrl = "/home/thushan/Desktop/paf2023/paf-assignment-2024-jun_we_29/gym_api/src/main/java/lk/jun_we_29/gym_api/photos/" + uploadDir + "/" + filename;
                fileUrls.add(fileUrl);
            } catch (IOException e) {
                throw new Exception("Faild to save file" + filename, e);
            }
        }


        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {

            Post post = new Post();
            post.setContent(content);
            post.setType(mediaType);
            if (!fileUrls.isEmpty()) {
                post.setMediaUrl(fileUrls.get(0));
            }

            User user = userOptional.get();
//            user.setId(user.getId());
            post.setUser(user);
            postRepository.save(post);
            return null;
        }
        return null;
    }


    @Override
    public List<CreatePostResponseDTO> getAllPost(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        List<Post> posts = postRepository.findAllByUserId(id);
        List<CreatePostResponseDTO> responseList = new ArrayList<>();
        for (Post post : posts) {
            CreatePostResponseDTO postDTO = new CreatePostResponseDTO();
            postDTO.setContent(post.getContent());
            postDTO.setMedia_type(post.getType());
            postDTO.setMedia_url(post.getMediaUrl());
            responseList.add(postDTO);
        }
        return responseList;
    }
}






