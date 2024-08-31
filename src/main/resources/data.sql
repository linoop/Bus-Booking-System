USE bus_booking_db;
CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       INT          NOT NULL
);

CREATE TABLE bus
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_number     VARCHAR(255) NOT NULL UNIQUE,
    bus_name       VARCHAR(255) NOT NULL,
    travel_company VARCHAR(255) NOT NULL,
    capacity       INT          NOT NULL,
    source         VARCHAR(255) NOT NULL,
    destination    VARCHAR(255) NOT NULL
);


CREATE TABLE booking
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT      NOT NULL,
    bus_id       BIGINT      NOT NULL,
    booking_number VARCHAR(255) NOT NULL UNIQUE,
    seats  INT         NOT NULL,
    booking_date DATETIME    NOT NULL,
    booking_status       VARCHAR(50) NOT NULL,
    source       VARCHAR(255) NOT NULL,
    destination       VARCHAR(255) NOT NULL,
    FOREIGN KEY (bus_id) REFERENCES bus (id)
);

