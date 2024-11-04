package school_admission_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school_admission_system.service.ApplicationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for specific origin
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("title") String title,
            @RequestParam("maritalStatus") String maritalStatus,
            @RequestParam("email") String email,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("dob") String dob,
            @RequestParam("gender") String gender,
            @RequestParam("placeOfBirth") String placeOfBirth,
            @RequestParam("nationality") String nationality,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("maidName") String maidName,
            @RequestParam("nationalId") String nationalId,
            @RequestParam("address") String address,
            @RequestParam("courseName") String courseName,
            @RequestParam(value = "document", required = true) MultipartFile document
    ) {
        // Check if any required fields are missing or invalid
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields.");
        }

        // Call the service to save the application
        applicationService.saveApplication(
                name, surname, title, maritalStatus, email, mobileNumber, dob, gender, placeOfBirth, nationality, country, city, maidName, nationalId, address, courseName, document
        );

        return ResponseEntity.ok("Application submitted successfully.");
    }

    @GetMapping("/download-pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        byte[] pdfContent = applicationService.getDocumentById(id);  // Get PDF from service by id

        if (pdfContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                    UriUtils.encode("application-document.pdf", StandardCharsets.UTF_8));
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
