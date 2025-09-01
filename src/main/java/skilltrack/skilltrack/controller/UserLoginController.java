package skilltrack.skilltrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skilltrack.skilltrack.dto.LoginRequesr;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("api/users")
public class UserLoginController {
    UserService userService;


    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login") // Используем POST для безопасности!
    public ResponseEntity<String> processLoginForm(@RequestBody LoginRequesr request) {
        boolean result = userService.verifyUser(request.getEmail(), request.getPassword());

        if (result) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("error");
        }

    }
}