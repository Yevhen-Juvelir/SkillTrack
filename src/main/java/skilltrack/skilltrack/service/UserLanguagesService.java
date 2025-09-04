package skilltrack.skilltrack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skilltrack.skilltrack.dto.LanguagesRequest;
import skilltrack.skilltrack.entity.UserLanguagesEntity;
import skilltrack.skilltrack.repository.UserLanguagesRepository;
import skilltrack.skilltrack.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserLanguagesService {

    private final UserLanguagesRepository userLanguagesRepository;
    private final UserRepository userRepository;

    public UserLanguagesService(UserLanguagesRepository userLanguagesRepository,
                                UserRepository userRepository) {
        this.userLanguagesRepository = userLanguagesRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean saveUserLanguages(String userEmail, List<LanguagesRequest> languages) {
        System.out.println("Saving user languages" + userEmail);
        if (!userRepository.existsByEmail(userEmail)) {
            return false;
        }

        // Получаем существующую запись или создаем новую
        Optional<UserLanguagesEntity> user_languages = userLanguagesRepository.findByEmail(userEmail);
        UserLanguagesEntity userLanguagesEntity = user_languages.orElse(new UserLanguagesEntity());
        userLanguagesEntity.setEmail(userEmail);



        // Сначала сбрасываем ВСЕ языки
        resetAllLanguages(userLanguagesEntity);

        // Затем устанавливаем уровни только для тех языков, которые пришли в запросе
        for (LanguagesRequest language : languages) {

            setLanguageLevel(userLanguagesEntity, language.getName(), language.getLevel());

        }

        userLanguagesRepository.save(userLanguagesEntity);
        return true;
    }


    private void resetAllLanguages(UserLanguagesEntity entity) {
        // Устанавливаем всем языкам null, чтобы сбросить предыдущие значения
        entity.setJavascript(null);
        entity.setPython(null);
        entity.setJava(null);
        entity.setCpp(null);
        entity.setCsharp(null);
        entity.setGo(null);
        entity.setRuby(null);
        entity.setPhp(null);
        entity.setTypescript(null);
        entity.setRust(null);
    }

    private void setLanguageLevel(UserLanguagesEntity entity, String languageName, String level) {
        System.out.println("Setting language level for " + languageName);
        switch (languageName.toLowerCase()) {
            case "javascript":
                entity.setJavascript(level);
                break;
            case "python":
                entity.setPython(level);
                break;
            case "java":
                entity.setJava(level);
                break;
            case "c++":
            case "cpp":
                entity.setCpp(level);
                break;
            case "c#":
            case "csharp":
                entity.setCsharp(level);
                break;
            case "go":
                entity.setGo(level);
                break;
            case "ruby":
                entity.setRuby(level);
                break;
            case "php":
                entity.setPhp(level);
                break;
            case "typescript":
                entity.setTypescript(level);
                break;
            case "rust":
                entity.setRust(level);
                break;
            default:
                // Можно добавить логирование для неизвестных языков
                break;
        }
    }

    public Optional<UserLanguagesEntity> getUserLanguages(String email) {
        return userLanguagesRepository.findById(email);
    }
}