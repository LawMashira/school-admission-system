package school_admission_system.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school_admission_system.entity.Application;
import school_admission_system.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
@CrossOrigin(origins = "https://maghandi-institute.vercel.app")
public class AdminController {

    private final ApplicationService applicationService;

    public AdminController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

  @GetMapping("/home")
    public String home() {
        return "Welcome to the School Admission System!";
    }
    // Fetch all applicants
    @GetMapping("/all")
    public ResponseEntity<List<Application>> getAllApplicants() {
        List<Application> applicants = applicationService.getAllApplications();
        return ResponseEntity.ok(applicants);
    }

    // Delete an applicant by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplicant(@PathVariable Long id) {
        try {
            applicationService.deleteApplicationById(id);
            return ResponseEntity.ok("Applicant deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting applicant: " + e.getMessage());
        }
    }
}


