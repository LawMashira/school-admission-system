package school_admission_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import school_admission_system.entity.UserProfile;

import java.util.Optional;


    public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
        Optional<UserProfile> findByEmail(String email);


}
