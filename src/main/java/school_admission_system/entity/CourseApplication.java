package school_admission_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applicationsCourse")
public class CourseApplication {





        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String applicantName;
        private String email;
        private String phone;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;

        private String qualifications;
        private String status; // "Pending", "Accepted", "Rejected"

        private String document; // Store document path or base64 string

        // Getters and Setters

}
