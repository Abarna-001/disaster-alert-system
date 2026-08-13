# disaster-alert-system
Real-time Disaster Alert Web Application
# 🚨 Disaster Preparedness & Emergency Response System

A full stack web application for real-time disaster alert management built with Java Spring Boot and React.js

---

## 🌟 Features

- 🚨 Real-time disaster alerts via WebSocket (STOMP Protocol)
- 🆘 SOS emergency reporting with automatic GPS location capture
- 🏠 Shelter information with interactive Leaflet map
- 🔐 JWT authentication and role-based access control
- 👥 Three roles — User, Authority, Admin
- 📊 Centralized admin dashboard for authorities
- 🗄️ PostgreSQL database with automatic table creation

---

## 🛠️ Tech Stack

### Backend
- Java 25
- Spring Boot 4.1
- Spring Security with JWT Authentication
- Spring Data JPA with Hibernate
- PostgreSQL Database
- WebSocket with STOMP Protocol
- Lombok

### Frontend
- React.js
- Tailwind CSS
- Leaflet.js Maps with OpenStreetMap
- SockJS and STOMP Client
- Axios

---

## 📁 Project Structure

---

## ⚙️ Setup Instructions

### Prerequisites
- Java 25
- Node.js
- PostgreSQL
- Maven

### Backend Setup

1. Create PostgreSQL database:
```sql
CREATE DATABASE disasterdb;
```

2. Create `application.properties` in `disaster-backend/src/main/resources/`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/disasterdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your-secret-key-256-bit
jwt.expiration=604800000
server.port=8081
```

3. Run backend:
```bash
cd disaster-backend
mvn spring-boot:run
```
Backend runs on **http://localhost:8081**

### Frontend Setup

1. Install dependencies:
```bash
cd disaster-frontend
npm install
```

2. Start frontend:
```bash
npm start
```
Frontend runs on **http://localhost:3000**

---

## 📡 API Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | /api/auth/register | Register new user | Public |
| POST | /api/auth/login | Login and get JWT token | Public |
| GET | /api/alerts/active | Get all active alerts | Public |
| POST | /api/alerts | Create disaster alert | Authority/Admin |
| PUT | /api/alerts/{id}/deactivate | Deactivate alert | Authority/Admin |
| POST | /api/sos/report | Submit SOS with GPS | Authenticated |
| GET | /api/sos/pending | Get pending SOS reports | Authority/Admin |
| GET | /api/shelters | Get available shelters | Public |
| POST | /api/shelters | Add new shelter | Authority/Admin |

---

## 👥 User Roles

| Role | Permissions |
|------|-------------|
| USER | View alerts, Submit SOS with GPS, View shelters |
| AUTHORITY | All USER permissions + Create alerts, Manage SOS, Add shelters |
| ADMIN | Full system access and user management |

---

## 🔐 Security Implementation

- JWT token based stateless authentication
- BCrypt password hashing
- Role based access control with manual role verification
- CORS configuration for frontend connection
- Spring Security filter chain with custom JwtFilter

---

## 🗄️ Database Schema

| Table | Description |
|-------|-------------|
| users | User accounts with roles |
| disaster_alerts | Active disaster alerts with type and severity |
| sos_reports | Emergency SOS reports with GPS coordinates |
| shelters | Available shelter locations with capacity |

---

## ⚡ Real-Time Feature

When an authority creates a disaster alert:
1. Alert saved to PostgreSQL database
2. AlertService broadcasts via SimpMessagingTemplate
3. WebSocket delivers to all connected users instantly
4. React frontend shows popup notification without page refresh

---

## 👩‍💻 Developer

**Abarna**

Disaster Preparedness and Emergency Response Web Application

**Role:** Backend Developer

**Tech:** Java · Spring Boot · JWT · WebSocket · PostgreSQL · React.js · Tailwind CSS · Leaflet.js
