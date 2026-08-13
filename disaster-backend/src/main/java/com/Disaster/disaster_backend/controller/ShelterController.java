package com.Disaster.disaster_backend.controller;

import com.Disaster.disaster_backend.model.Shelter;
import com.Disaster.disaster_backend.repository
        .ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost
        .PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ShelterController {

    private final ShelterRepository shelterRepo;

    // ✅ Anyone can VIEW shelters
    @GetMapping
    public ResponseEntity<List<Shelter>> getAll() {
        return ResponseEntity.ok(
                shelterRepo.findByAvailableTrue());
    }

    // 🔒 ONLY AUTHORITY and ADMIN can ADD
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_AUTHORITY') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Shelter> create(
            @RequestBody Shelter shelter) {
        return ResponseEntity.ok(
                shelterRepo.save(shelter));
    }

    // 🔒 ONLY AUTHORITY and ADMIN can UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_AUTHORITY') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Shelter> update(
            @PathVariable Long id,
            @RequestBody Shelter updated) {
        Shelter shelter = shelterRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Shelter not found"));
        shelter.setName(updated.getName());
        shelter.setAddress(updated.getAddress());
        shelter.setContactNumber(
                updated.getContactNumber());
        shelter.setTotalCapacity(
                updated.getTotalCapacity());
        shelter.setCurrentOccupancy(
                updated.getCurrentOccupancy());
        shelter.setAvailable(updated.getAvailable());
        shelter.setFacilities(updated.getFacilities());
        return ResponseEntity.ok(
                shelterRepo.save(shelter));
    }

    // 🔒 ONLY AUTHORITY and ADMIN can DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_AUTHORITY') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {
        shelterRepo.deleteById(id);
        return ResponseEntity.ok(
                "Shelter deleted successfully");
    }
}