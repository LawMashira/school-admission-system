package school_admission_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school_admission_system.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}