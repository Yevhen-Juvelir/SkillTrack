package skilltrack.skilltrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skilltrack.skilltrack.entity.UserEntity;
import skilltrack.skilltrack.entity.UserLanguagesEntity;

import java.util.Optional;


public interface UserLanguagesRepository extends JpaRepository<UserLanguagesEntity, String> {
    Optional<UserLanguagesEntity> findByEmail(String email);
}