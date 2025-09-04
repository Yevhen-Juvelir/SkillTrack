package skilltrack.skilltrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import skilltrack.skilltrack.dto.LanguagesRequest;
import skilltrack.skilltrack.dto.SaveLanguagesRequest;
import skilltrack.skilltrack.entity.UserLanguagesEntity;
import skilltrack.skilltrack.repository.UserLanguagesRepository;
import skilltrack.skilltrack.repository.UserRepository;
import skilltrack.skilltrack.service.UserLanguagesService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/users")
public class AddLanguagesPageController {

    private final UserRepository userRepository;
    private final UserLanguagesRepository userLanguagesRepository;
    private final UserLanguagesService userLanguagesService;

    public AddLanguagesPageController(UserRepository userRepository,
                                      UserLanguagesRepository userLanguagesRepository, UserLanguagesService userLanguagesService) {
        this.userRepository = userRepository;
        this.userLanguagesRepository = userLanguagesRepository;
        this.userLanguagesService = userLanguagesService;
    }

    @PostMapping("/save_languages")
    public ResponseEntity<String> saveLanguages(@RequestBody SaveLanguagesRequest request) {
        String user_email = request.getUser_email();
        System.out.println(user_email);
        List<LanguagesRequest> languages = request.getLanguages();

        // Проверяем, существует ли пользователь
        if (!userRepository.existsByEmail(user_email)) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }

        try {
            userLanguagesService.saveUserLanguages(user_email, languages);
            return ResponseEntity.ok("Языки успешно сохранены");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при сохранении языков: " + e.getMessage());
        }
    }
}