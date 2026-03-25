# Event Ticketing System
### CPSC 449 — Web Backend Engineering | Cal State Fullerton
### Midterm Project


## Team Members

| Name | CWID      |
|------|-----------|
| Bryan Lopez Levia | 821009883 |
| Daniel DiPietrantonio | CWID      |
| Ryan Oskuie | 885384289      |
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
| POST | `/api/events/{evntId}/tickettypes` | Create a ticket type for an event |
| Get | `/api/events/{eventId}/tickettypes` | Get all ticket types for an event | 

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
http://localhost:8080/api/events?organizerId=4&venueId=3
```json
{
    "title": "World Cup semi-Final 1",
    "eventDate": "2026-07-14T12:00:00",
    "description": "The first semi-Final match of the 2026 FIFA World Cup"
}
```
<img width="1918" height="1032" alt="image" src="https://github.com/user-attachments/assets/a64aee87-b545-452c-a602-ce23e0a107ff" />


### GET /api/events
http://localhost:8080/api/events
<img width="1919" height="1036" alt="image" src="https://github.com/user-attachments/assets/af874c8b-9f5e-4aa1-a02f-383c386133c6" />


### GET /api/events/{id}
http://localhost:8080/api/events/2
<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/3febef9a-21d8-4098-acde-b25359fc0338" />


### POST /api/attendees
http://localhost:8080/api/attendees
```json
{
    "name": "Attendee 1",
    "email": "attendee1@example.com"
}
```
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/1d2e2499-7f5d-4e79-ade0-a5b51323cefe" />


### POST /api/bookings
http://localhost:8080/api/bookings?attendeeId=1&ticketTypeId=2
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/5994534e-bfd0-443a-822c-897fa77ed656" />
<img width="1916" height="1033" alt="image" src="https://github.com/user-attachments/assets/175aeba6-7fbb-4589-8322-1702d70a559c" />



### PUT /api/bookings/{id}/cancel
POST http://localhost:8080/api/bookings?attendeeId=5&ticketTypeId=4
<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/b579a6ce-844a-40a3-8bfd-3041cb524090" />
GET http://localhost:8080/api/events
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/6e336f84-dd4a-4a0f-b492-4e6f1655382e" />
PUT http://localhost:8080/api/bookings/6/cancel
<img width="1916" height="1034" alt="image" src="https://github.com/user-attachments/assets/d6ef1f24-0009-49d3-a307-509a3009796f" />
GET http://localhost:8080/api/events
<img width="1919" height="1031" alt="image" src="https://github.com/user-attachments/assets/30674833-fec1-4128-90b4-84645275ae3a" />



### GET /api/events/{id}/revenue
http://localhost:8080/api/events/2/revenue
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/c157dcce-da4d-47c1-a635-1abb13f37baa" />


### GET /api/attendees/{id}/bookings
http://localhost:8080/api/attendees/1/bookings
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/7373bfcf-1ec2-43b4-8c10-0f3790ab1f4e" />


### POST /api/events/{evntId}/tickettypes
http://localhost:8080/api/events/2/tickettypes
```json
{
    "name": "General Admission",
    "price": 50.00,
    "quantityAvailable": 10
}
```
<img width="1919" height="1034" alt="image" src="https://github.com/user-attachments/assets/5eb4eacb-e3f8-4fe8-9255-08870ea0b653" />


### GET /api/events/{evntId}/tickettypes
http://localhost:8080/api/events/2/tickettypes
<img width="1919" height="1033" alt="image" src="https://github.com/user-attachments/assets/b9b1ec4e-efa3-4eb6-b54f-9c8395708984" />


---
