package skilltrack.skilltrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import skilltrack.skilltrack.service.UserService;

@Controller
@RequestMapping("/users")
public class UserLoginController {
    UserService userService;


    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Просто показываем форму логина
    }

    @PostMapping("/login") // Используем POST для безопасности!
    public String processLoginForm(
            @RequestParam String email,
            @RequestParam String password) {

        System.out.println("=== ПОЛУЧЕНЫ ДАННЫЕ РЕГИСТРАЦИИ ===");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        if(userService.verifyUser(email, password)){
            return "redirect:/"; // Успешный логин
        } else {
            return "redirect:/users/login?error"; // Ошибка логина
        }
    }
}