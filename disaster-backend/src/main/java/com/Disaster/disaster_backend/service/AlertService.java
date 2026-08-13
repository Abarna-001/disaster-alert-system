package com.Disaster.disaster_backend.service;

import com.Disaster.disaster_backend.dto.AlertNotification;
import com.Disaster.disaster_backend.model.DisasterAlert;
import com.Disaster.disaster_backend.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepo;
    private final SimpMessagingTemplate messagingTemplate;

    public DisasterAlert createAlert(DisasterAlert alert) {
        // Save to database
        DisasterAlert saved = alertRepo.save(alert);

        // Build notification
        AlertNotification notification = AlertNotification
                .builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .location(saved.getLocation())
                .type(saved.getType() != null ?
                        saved.getType().name() : "OTHER")
                .severity(saved.getSeverity() != null ?
                        saved.getSeverity().name() : "MEDIUM")
                .createdAt(saved.getCreatedAt())
                .message("🚨 New Alert: " + saved.getTitle())
                .build();

        // Broadcast to all users via WebSocket
        messagingTemplate.convertAndSend(
                "/topic/alerts", notification);

        // Extra broadcast for CRITICAL alerts
        if (saved.getSeverity() != null &&
                saved.getSeverity().name().equals("CRITICAL")) {
            messagingTemplate.convertAndSend(
                    "/topic/critical", notification);
        }

        return saved;
    }

    public void deactivateAlert(Long id) {
        DisasterAlert alert = alertRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Alert not found"));
        alert.setActive(false);
        alertRepo.save(alert);

        AlertNotification notification = AlertNotification
                .builder()
                .id(alert.getId())
                .title(alert.getTitle())
                .message("✅ Alert Resolved: " +
                        alert.getTitle())
                .build();

        messagingTemplate.convertAndSend(
                "/topic/alerts-resolved", notification);
    }
}