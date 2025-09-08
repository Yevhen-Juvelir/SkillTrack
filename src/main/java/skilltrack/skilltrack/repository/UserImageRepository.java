package skilltrack.skilltrack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skilltrack.skilltrack.entity.UserImageEntity;

@Repository
public interface UserImageRepository extends JpaRepository<UserImageEntity, Integer> {
    boolean existsByEmail(String userEmail);
    UserImageEntity findByEmail(String email);
}
