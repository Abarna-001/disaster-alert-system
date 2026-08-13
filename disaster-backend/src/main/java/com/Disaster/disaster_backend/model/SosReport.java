package com.Disaster.disaster_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sos_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SosReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SosStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportedBy;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        status = SosStatus.PENDING;
    }
}
