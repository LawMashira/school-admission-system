package school_admission_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Entity
@Table(name = "user_profile")
@CrossOrigin("http://localhost:3000")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    @Lob
    private byte[] profilePicture; // Store profile picture as a byte array (BLOB)

    @Lob
    private byte[] coverPhoto; // Store cover photo as a byte array (BLOB)

    // Getters and Setters
    // ...
}
