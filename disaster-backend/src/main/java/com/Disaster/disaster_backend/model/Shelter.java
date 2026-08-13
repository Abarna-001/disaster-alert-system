package com.Disaster.disaster_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shelters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactNumber;
    private int totalCapacity;
    private int currentOccupancy;
    private Double latitude;
    private Double longitude;
    private Boolean available = true;
    private String facilities;
}