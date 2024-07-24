package lk.jun_we_29.gym_api.service;

import lk.jun_we_29.gym_api.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> createUser(User user);

    ResponseEntity<Object> loginUser(String email, String password);
}
