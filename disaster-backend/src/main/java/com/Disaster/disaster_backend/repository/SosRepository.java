package com.Disaster.disaster_backend.repository;

import com.Disaster.disaster_backend.model.SosReport;
import com.Disaster.disaster_backend.model.SosStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SosRepository extends JpaRepository<SosReport, Long> {
    List<SosReport> findByStatus(SosStatus status);
}