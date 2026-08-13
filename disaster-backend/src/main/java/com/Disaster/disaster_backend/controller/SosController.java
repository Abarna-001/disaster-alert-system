package com.Disaster.disaster_backend.controller;

import com.Disaster.disaster_backend.model.*;
import com.Disaster.disaster_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation
        .AuthenticationPrincipal;
import org.springframework.security.core.userdetails
        .UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SosController {

    private final SosRepository sosRepo;
    private final UserRepository userRepo;

    @PostMapping("/report")
    public ResponseEntity<?> report(
            @RequestBody SosRequest request,
            @AuthenticationPrincipal
            UserDetails userDetails) {
        try {
            User user = userRepo
                    .findByEmail(userDetails.getUsername())
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            SosReport report = new SosReport();
            report.setMessage(request.getMessage());
            report.setLatitude(request.getLatitude());
            report.setLongitude(request.getLongitude());
            report.setReportedBy(user);

            return ResponseEntity.ok(sosRepo.save(report));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPending() {
        return ResponseEntity.ok(
                sosRepo.findByStatus(SosStatus.PENDING));
    }

    @PutMapping("/{id}/acknowledge")
    public ResponseEntity<?> acknowledge(
            @PathVariable Long id) {
        SosReport report = sosRepo.findById(id)
                .orElseThrow();
        report.setStatus(SosStatus.ACKNOWLEDGED);
        sosRepo.save(report);
        return ResponseEntity.ok("Acknowledged");
    }
}