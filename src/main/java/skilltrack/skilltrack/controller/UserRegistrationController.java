package skilltrack.skilltrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("/users") // Базовый путь для всех endpoints пользователей
public class UserRegistrationController {

    private final UserService userService;


    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        System.out.println("Показана форма регистрации");
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username) {

        System.out.println("=== ПОЛУЧЕНЫ ДАННЫЕ РЕГИСТРАЦИИ ===");
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        boolean result = userService.saveUser(email, password, username);
        System.out.println("Результат сохранения: " + result);

        if (result) {
            System.out.println("Регистрация успешна");
            return "redirect:/users/login";
        } else {
            System.out.println("Ошибка регистрации");
            return "redirect:/users/register?error";
        }
    }
}