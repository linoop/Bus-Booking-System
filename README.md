# Bus Booking System

This is a simple bus booking system developed using Kotlin and Spring Boot. The application allows users to perform basic operations such as creating, updating, and fetching bus details, searching for buses between two locations, and booking seats on available buses.

## Features

- **Bus Management**
  - Create, update, and fetch bus details.
- **User Management**
  - Predefined users stored in the database.
  - Users can log in using static JWT tokens.
- **Bus Search**
  - Search for available buses between a source and destination.
- **Booking Management**
  - Book seats on available buses.
  - Generate random booking reference strings.

## Technologies Used

- **Backend:**
  - Spring Boot
  - Kotlin
  - Spring Data JPA (without Hibernate)
  - Spring Security
  - Spring Web
  - MySQL (8.4.2)
  - JDBC Template

- **Database:**
  - MySQL

## Setup and Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/bus-booking-system.git
   cd bus-booking-system
   ```

2. **Set Up the Database**
   - Ensure MySQL is installed and running.
   - Create a database named `bus_booking_db`.
   - Update the database connection properties in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/bus_booking_db
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     ```

3. **Build and Run the Application**
   ```bash
   ./gradlew bootRun
   ```

4. **Access the Application**
   - The application runs by default on `http://localhost:8080`.

## API Endpoints

### Authentication

- **Login (Static JWT Token):**
  - Endpoint: `/api/authentication`
  - Method: `POST`
  - Use predefined users stored in the database.

### Bus Management

- **Create Bus:**
  - Endpoint: `/api/bus`
  - Method: `POST`

- **Update Bus:**
  - Endpoint: `/api/bus/{id}`
  - Method: `PUT`

- **Fetch Bus Details:**
  - Endpoint: `/api/bus/{id}`
  - Method: `GET`

### Booking Management

- **Book a Seat:**
  - Endpoint: `/api/system/book`
  - Method: `POST`
 
- **Search Buses:**
  - Endpoint: `/api/system/search`
  - Method: `GET`
  - Parameters: `source`, `destination`

## Example Configuration

### `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bus_booking_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JWT configuration
jwt.secret=your_secret_key
jwt.token.prefix=Bearer 
jwt.header=Authorization
```

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

This README provides an overview of the application, how to set it up, and key information on usage and contributing. Adjust the information as necessary to fit your project's specifics.
