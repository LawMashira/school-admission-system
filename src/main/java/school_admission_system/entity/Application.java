package school_admission_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String title;
    private String maritalStatus;
    private String email;
    private String mobileNumber;
    private String dob;
    private String gender;
    private String placeOfBirth;
    private String nationality;
    private String country;
    private String city;
    private String maidName;
    private String nationalId;
    private String address;
    private String courseName;  // Add course name

    @Lob
    private byte[] document;  // Store document (PDF) as a byte array (BLOB)

}
