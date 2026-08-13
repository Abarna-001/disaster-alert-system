package com.Disaster.disaster_backend.repository;

import com.Disaster.disaster_backend.model.Shelter;
import org.springframework.data.jpa.repository
        .JpaRepository;
import java.util.List;

public interface ShelterRepository
        extends JpaRepository<Shelter, Long> {
    List<Shelter> findByAvailableTrue();
}