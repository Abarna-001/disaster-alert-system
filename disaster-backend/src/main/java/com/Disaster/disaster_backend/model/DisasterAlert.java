package com.Disaster.disaster_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disaster_alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisasterAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AlertType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private Severity severity;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (active == null) active = true;
    }
}