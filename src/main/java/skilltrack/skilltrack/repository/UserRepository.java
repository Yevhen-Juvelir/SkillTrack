package skilltrack.skilltrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skilltrack.skilltrack.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}