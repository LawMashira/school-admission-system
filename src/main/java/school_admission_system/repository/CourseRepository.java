package school_admission_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school_admission_system.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
