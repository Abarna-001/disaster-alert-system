package com.Disaster.disaster_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertNotification {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String severity;
    private LocalDateTime createdAt;
    private String message;
}