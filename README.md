# Event Ticketing System
### CPSC 449 — Web Backend Engineering | Cal State Fullerton
### Midterm Project


## Team Members

| Name | CWID      |
|------|-----------|
| Bryan Lopez Levia | 821009883 |
| Daniel DiPietrantonio | CWID      |
| Ryan Oskuie | CWID      |
| Roberto Manra | 868912841 |

---

## Project Overview

This is a RESTful backend API for an Event Ticketing System built with Spring Boot, JPA, and PostgreSQL. This system manages events, venues, organizers, ticket types, attendees, and bookings.

---

## Demo Video

[Watch a demo](<!--YT link -->)


## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/organizers` | Create a new organizer |
| POST | `/api/venues` | Create a new venue |
| POST | `/api/events` | Create a new event |
| GET | `/api/events` | List all upcoming events |
| GET | `/api/events/{id}` | Get event details with ticket types |
| POST | `/api/attendees` | Register a new attendee |
| POST | `/api/bookings` | Book a ticket |
| PUT | `/api/bookings/{id}/cancel` | Cancel a booking |
| GET | `/api/events/{id}/revenue` | Get total revenue for an event |
| GET | `/api/attendees/{id}/bookings` | Get all bookings for an attendee |

---

## API Screenshots

### POST /api/organizers
http://localhost:8080/api/organizers
input: 
```json
{
    "emial": "contact@fifa.com",
    "name": "FIFA",
    "number": "555-0300"
}
```
output: 
<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/65bdbf7c-d597-4735-8a88-5b4cef301f83" />
<img width="1919" height="1035" alt="image" src="https://github.com/user-attachments/assets/a858091d-4e86-4805-ad87-2b6b9f8d2a6a" />



### POST /api/venues
http://localhost:8080/api/venues
```json
{
    "address": "1 MetLife Stadium Dr",
    "city": "EastRutherford",
    "name": "MetLife Stadium",
    "total_capacity": 82500
}
```
Output:
<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/7364e477-e0d0-4fa6-9400-8f14ff3a10ea" />
<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/6b0c9e80-0375-4e30-ac68-bd8420ad896d" />


### POST /api/events
<!-- Add screenshot here -->

### GET /api/events
<!-- Add screenshot here -->

### GET /api/events/{id}
<!-- Add screenshot here -->

### POST /api/attendees
<!-- Add screenshot here -->

### POST /api/bookings
<!-- Add screenshot here -->

### PUT /api/bookings/{id}/cancel
<!-- Add screenshot here -->

### GET /api/events/{id}/revenue
<!-- Add screenshot here -->

### GET /api/attendees/{id}/bookings
<!-- Add screenshot here -->

---
