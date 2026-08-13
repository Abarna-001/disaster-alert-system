package com.Disaster.disaster_backend.controller;

import lombok.Data;
@Data
class LoginRequest {
    private String email, password;
}