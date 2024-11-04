package school_admission_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school_admission_system.entity.UserProfile;
import school_admission_system.repository.UserProfileRepository;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // List of allowed file types
    private static final String[] ALLOWED_CONTENT_TYPES = {"image/jpeg", "image/png", "image/gif"};

    public UserProfile getUserProfile(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUserProfile(Long id, String firstName, String lastName, String email, String phone,
                                  String address, String city, String state, String zipCode,
                                  MultipartFile profilePicture, MultipartFile coverPhoto) throws IOException {

        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setEmail(email);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
        userProfile.setCity(city);
        userProfile.setState(state);
        userProfile.setZipCode(zipCode);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            validateImage(profilePicture); // Validate the image format
            userProfile.setProfilePicture(profilePicture.getBytes());
        }

        if (coverPhoto != null && !coverPhoto.isEmpty()) {
            validateImage(coverPhoto); // Validate the image format
            userProfile.setCoverPhoto(coverPhoto.getBytes());
        }

        userProfileRepository.save(userProfile);
    }

    private void validateImage(MultipartFile file) {
        String contentType = file.getContentType();
        if (!Arrays.asList(ALLOWED_CONTENT_TYPES).contains(contentType)) {
            throw new IllegalArgumentException("Invalid file type: " + contentType);
        }
    }
}
