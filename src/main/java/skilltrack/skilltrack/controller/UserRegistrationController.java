package skilltrack.skilltrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skilltrack.skilltrack.dto.RegisterRequest;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("api/users") // Базовый путь для всех endpoints пользователей
public class UserRegistrationController {

    private final UserService userService;


    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        boolean result = userService.saveUser(request.getEmail(), request.getPassword(), request.getName());

        if (result) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("Ошибка регистрации");
        }
    }
}