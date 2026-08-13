package com.Disaster.disaster_backend.repository;

import com.Disaster.disaster_backend.model.DisasterAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
    public interface AlertRepository extends JpaRepository<DisasterAlert, Long> {
        List<DisasterAlert> findByActiveTrue();
    }


