package lk.jun_we_29.gym_api.controller;

import jakarta.servlet.http.HttpServletResponse;
import lk.jun_we_29.gym_api.controller.dto.request.UserDTO;
import lk.jun_we_29.gym_api.model.RegistrationSource;
import lk.jun_we_29.gym_api.model.User;
import lk.jun_we_29.gym_api.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller

public class AuthController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("/looooo" + principal);
        return "redirect:http://localhost:3000/Home";
    }

    @GetMapping("/api/user")
    @ResponseBody
    public ResponseEntity<Object> getUsername(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            String picture = principal.getAttribute("picture");
            User user = new User();
            user.setEmail(email);
            user.setUsername(name);
            user.setProfilePictureUrl(picture);
            user.setSource(RegistrationSource.GOOGLE);

            return userService.createUser(user);
        } else {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        }
    }
}
