package school_admission_system.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school_admission_system.entity.Application;
import school_admission_system.repository.ApplicationRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void saveApplication(
            String name,
            String surname,
            String title,
            String maritalStatus,
            String email,
            String mobileNumber,
            String dob,
            String gender,
            String placeOfBirth,
            String nationality,
            String country,
            String city,
            String maidName,
            String nationalId,
            String address,
            String courseName,
            MultipartFile document
    ) {
        // Create a new Application entity
        Application application = new Application();
        application.setName(name);
        application.setSurname(surname);
        application.setTitle(title);
        application.setMaritalStatus(maritalStatus);
        application.setEmail(email);
        application.setMobileNumber(mobileNumber);
        application.setDob(dob);
        application.setGender(gender);
        application.setPlaceOfBirth(placeOfBirth);
        application.setNationality(nationality);
        application.setCountry(country);
        application.setCity(city);
        application.setMaidName(maidName);
        application.setNationalId(nationalId);
        application.setAddress(address);
        application.setCourseName(courseName);

        try {
            // Convert the MultipartFile to a byte array and set it in the entity
            if (document != null && !document.isEmpty()) {
                application.setDocument(document.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save document file", e);
        }

        // Save the application entity to the database
        applicationRepository.save(application);
    }


    // Fetch all applicants
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Delete an applicant by ID
    public void deleteApplicationById(Long id) {
        applicationRepository.deleteById(id);
    }

    public byte[] getDocumentById(Long id) {
        return applicationRepository.findById(id)
                .map(Application::getDocument)
                .orElse(null);  // Get the document as byte array (null if not found)
    }
}
