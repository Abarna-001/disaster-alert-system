package com.Disaster.disaster_backend.controller;

import com.Disaster.disaster_backend.model.DisasterAlert;
import com.Disaster.disaster_backend.model.User;
import com.Disaster.disaster_backend.repository.AlertRepository;
import com.Disaster.disaster_backend.repository.UserRepository;
import com.Disaster.disaster_backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AlertController {

    private final AlertRepository alertRepo;
    private final AlertService alertService;
    private final UserRepository userRepo;

    @GetMapping("/active")
    public ResponseEntity<List<DisasterAlert>> getActive() {
        return ResponseEntity.ok(
                alertRepo.findByActiveTrue());
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody DisasterAlert alert,
            @AuthenticationPrincipal
            UserDetails userDetails) {
        try {
            System.out.println("Creating alert for: " +
                    userDetails.getUsername());

            User user = userRepo
                    .findByEmail(userDetails.getUsername())
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            System.out.println("User role: " +
                    user.getRole());

            // Role check using string comparison
            String role = user.getRole().name();
            if (!role.equals("AUTHORITY") &&
                    !role.equals("ADMIN")) {
                return ResponseEntity.status(403)
                        .body("Access denied. Role: " + role);
            }

            alert.setCreatedBy(user);
            DisasterAlert saved =
                    alertService.createAlert(alert);

            System.out.println("Alert created: " +
                    saved.getId());

            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            System.out.println("Alert creation error: " +
                    e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivate(
            @PathVariable Long id,
            @AuthenticationPrincipal
            UserDetails userDetails) {
        try {
            User user = userRepo
                    .findByEmail(userDetails.getUsername())
                    .orElseThrow();
            String role = user.getRole().name();
            if (!role.equals("AUTHORITY") &&
                    !role.equals("ADMIN")) {
                return ResponseEntity.status(403)
                        .body("Access denied.");
            }
            alertService.deactivateAlert(id);
            return ResponseEntity.ok(
                    "Alert deactivated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error: " + e.getMessage());
        }
    }
}