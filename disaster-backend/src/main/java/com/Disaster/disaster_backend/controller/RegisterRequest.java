package com.Disaster.disaster_backend.controller;

import lombok.Data;
@Data
class RegisterRequest {
    private String name, email, password, phone;
}