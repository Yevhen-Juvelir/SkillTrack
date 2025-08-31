package skilltrack.skilltrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("/users") // Базовый путь для всех endpoints пользователей
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username) {

        if (userService.saveUser(email, password, username)) {
            return "redirect:/users/login";
        } else {
            return "redirect:/users/register?error";
        }
    }

    // Переброс на странцу профиля
    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }
}