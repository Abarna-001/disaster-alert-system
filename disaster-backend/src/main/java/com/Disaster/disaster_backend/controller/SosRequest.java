package com.Disaster.disaster_backend.controller;

import lombok.Data;

@Data
public class SosRequest {
    private String message;
    private Double latitude;
    private Double longitude;
}