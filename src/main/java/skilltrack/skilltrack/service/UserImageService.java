package skilltrack.skilltrack.service;

import org.springframework.stereotype.Service;
import skilltrack.skilltrack.entity.UserImageEntity;
import skilltrack.skilltrack.repository.UserImageRepository;

// Сервис для сохранения аватара пользователя

@Service
public class UserImageService {

    private final UserImageRepository userImageRepository;

    public UserImageService(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }
    // Создание и сохранение аватара пользователя
    public void updateUserImage(String user_email, String image_url) {

        if (userImageRepository.existsByEmail(user_email)) {
            UserImageEntity userImageEntity = userImageRepository.findByEmail(user_email);
            if (userImageEntity.getImage_url().equals(image_url)) {
                return;
            } else {
                userImageEntity.setImage_url(image_url);
                userImageRepository.save(userImageEntity);
            }
        } else {
            UserImageEntity userImageEntity = new UserImageEntity();
            userImageEntity.setUser_email(user_email);
            userImageEntity.setImage_url(image_url);
            userImageRepository.save(userImageEntity);
        }


    }

    public String getUserImage(String user_email) {
        if (userImageRepository.existsByEmail(user_email)) {
            UserImageEntity userImageEntity = userImageRepository.findByEmail(user_email);
            return userImageEntity.getImage_url();
        }
        return null;
    }

}
