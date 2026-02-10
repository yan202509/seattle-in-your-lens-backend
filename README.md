# 📸 Seattle In Your Lens – Backend

Backend service for **Seattle In Your Lens**, a community-driven web app where users can **find, share, and review events** in Seattle.
This API handles **event data**, **user accounts**, and **reviews**, providing the core functionality for the frontend to display events and collect user feedback.
---

## Tech Stack

- **Language:** Java 
- **Framework:** Spring Boot 
- **Persistence:** Spring Data JPA
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Security:** Basic mock authentication (login simulation; JWT not implemented yet)
- **Deployment:** Railway

---

## Architecture

The application follows a standard **Layered Architecture** to ensure separation of concerns:

1. **Controller Layer:** Acts as the REST API interface, handling HTTP requests and returning JSON data directly from JPA Entities.
2. **Service Layer:** Contains the business logic (e.g., handling likes, search logic, and permission checks).
3. **Repository Layer:** Interacts with the PostgreSQL database using Spring Data JPA for CRUD operations and custom search queries.

---

## API Endpoints

### Events
| Method                 | Endpoint                | Description                                |
|:-----------------------|:------------------------|:-------------------------------------------|
| **GET**                | `/api/events`           | Get all events (supports search/filtering) |
| **GET**                | `/api/events/{id}`      | Get specific event details and reviews     |
| **POST**               | `/api/events`           | Create a new event                         |
| **DELETE**             | `/api/events/{id}`      | Remove an event (Admin or Creator only)    |
| **PATCH**              | `/api/events/{id}/like` | Increment the like counter for an event    |
| **GET**                | `/api/events/search `   | Search events by query string   |

### Reviews
| Method | Endpoint | Description |
|:--- |:--- |:--- |
| **POST** | `/api/events/{id}/reviews` | Add a rating (1–5) and comment |
| **GET** | `/api/events/{id}/reviews` | Retrieve all reviews for a specific event |

### Authentication
| Method | Endpoint     | Description |
|:--- |:-------------|:--- |
| **POST** | `/api/login` | Validates credentials and returns User details |

---

# Database Schema

The backend of **Seattle In Your Lens** uses a relational database with three core entities: **User**, **Event**, and **Review**. These entities are connected via standard one-to-many and many-to-one relationships to support event creation and user reviews.

---

## **User**
Represents an application user who can create events.

**Fields**
- `id` (Long, PK)
- `username` (String)
- `password` (String) Note: Plain text now; will be implemented
- `role` (String)

**Relationships**
- One **User** can create many **Events**
- A **User** is the creator of an **Event**

---

## **Event**
Represents an experience-based event within Seattle.

**Fields**
- `event_id` (Long, PK)
- `eventTitle` (String)
- `event_description` (String)
- `event_season` (String)
- `event_type` (String) — indoor / outdoor
- `cost_level` (String)
- `event_date` (LocalDateTime)
- `likes` (Integer)

**Relationships**
- Many **Events** belong to one **User** (creator)
- One **Event** can have many **Reviews**
- Deleting an **Event** will also delete its associated **Reviews** (orphan removal)

---

## **Review**
Represents user feedback associated with a specific event.

**Fields**
- `id` (Long, PK)
- `rating` (Integer, 1–5)
- `comment` (String)
- `createdAt` (LocalDateTime)

**Relationships**
- Many **Reviews** belong to one **Event**
- Each **Review** must be associated with an **Event**

---

## Entity Relationship Overview (ER-Style)

User
└───< creates
Event
└───< has
Review

## Running the Application
- Using IntelliJ IDEA (Recommended)
- Open the project in IntelliJ.
- Let Maven import all dependencies 
- Open SeattleInYourLensApplication.java.
Click the green Run button.


## Environment Variables

To run this project locally, create an `application.properties` file or set the following variables:

```env
spring.application.name=seattle-in-your-lens
spring.profiles.active=local

# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/seattle_lens_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_LOCAL_DB_PASSWORD

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Port
server.port=8080


