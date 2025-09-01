package skilltrack.skilltrack.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import skilltrack.skilltrack.entity.UserEntity;
import skilltrack.skilltrack.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(String email, String password, String username) {
        try {
            // Проверяем, нет ли уже пользователя с таким email ИЛИ username
            if (userRepository.findByEmail(email) != null) {
                System.out.println("Пользователь с email " + email + " уже существует");
                return false;
            }

            if (userRepository.findByUsername(username) != null) {
                System.out.println("Пользователь с username " + username + " уже существует");
                return false;
            }
            System.out.println(password);
            // Создаем нового пользователя
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password)); // Хешируем пароль
            user.setUsername(username);
            user.setExp(0);
            user.setLvl(1);
            user.setRole(false);

            // Сохраняем в базу
            userRepository.save(user);
            System.out.println("Пользователь успешно сохранен: " + username);
            return true;

        } catch (Exception e) {
            System.out.println("Ошибка при сохранении пользователя: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyUser(String email, String password) {
        try {
            System.out.println("начало проверки" + email + " " + password);
            UserEntity user = userRepository.findByEmailIgnoreCase(email.trim());
            System.out.println("Пользователь найден: " + (user != null ? user.getEmail() : "null"));

            if (user == null) {
                System.out.println("Пользователь с email " + email + " не найден");
                return false;
            }

            boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
            System.out.println(passwordMatches);
            if (!passwordMatches) {
                System.out.println("Неверный пароль для пользователя: " + email);
            }
            return passwordMatches;

        } catch (Exception e) {
            System.out.println("Ошибка при проверке пользователя: " + e.getMessage());
            return false;
        }
    }

    // Вспомогательные методы (можно удалить если не используются)
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}