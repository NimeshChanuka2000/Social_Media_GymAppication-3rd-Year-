package lk.jun_we_29.gym_api.service.impl;

import lk.jun_we_29.gym_api.controller.dto.request.UserDTO;
import lk.jun_we_29.gym_api.controller.dto.response.UserResDTO;
import lk.jun_we_29.gym_api.model.RegistrationSource;
import lk.jun_we_29.gym_api.model.User;
import lk.jun_we_29.gym_api.repository.UserRepository;
import lk.jun_we_29.gym_api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Object> createUser(User user) {

        if(user.getSource() == null){
            if (userRepository.existsByEmail(user.getEmail())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setSource(RegistrationSource.CREDENTIAL);
            User savedUser = userRepository.save(user);
            UserDTO savedUserDTO = new UserDTO();
            BeanUtils.copyProperties(savedUser, savedUserDTO);
            return new ResponseEntity<>("Register Successfully", HttpStatus.OK);
        }

        if(Objects.equals(user.getSource(), RegistrationSource.GOOGLE)){

            String email = user.getEmail();
            if (userRepository.existsByEmail(email)) {
                User googleUser = userRepository.findByEmail(email);
                UserDTO userDto = new UserDTO();
                BeanUtils.copyProperties(googleUser, userDto);
                return  new ResponseEntity<>(userDto, HttpStatus.OK);
            }

            User googleUser = new User();
            googleUser.setUsername(user.getUsername());
            googleUser.setEmail(user.getEmail());
            googleUser.setProfilePictureUrl(user.getProfilePictureUrl());
            googleUser.setSource(RegistrationSource.GOOGLE);
            try {
                userRepository.save(googleUser);
                UserResDTO userDto = new UserResDTO();
                BeanUtils.copyProperties(googleUser, userDto);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<Object> loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return new ResponseEntity<>("Invalid password or email", HttpStatus.UNAUTHORIZED);
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            UserResDTO userDto = new UserResDTO();
            BeanUtils.copyProperties(user, userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid password or email", HttpStatus.UNAUTHORIZED);
        }
    }
}
