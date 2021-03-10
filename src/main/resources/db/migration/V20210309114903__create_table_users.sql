CREATE TABLE users
(
    id             SERIAL PRIMARY KEY     NOT NULL,
    avatar         VARCHAR(200)           NOT NULL,
    first_name     VARCHAR(50)            NOT NULL,
    last_name      VARCHAR(50)            NOT NULL,
    email          VARCHAR(50)            NOT NULL,
    city           VARCHAR(50),
    phone_number   VARCHAR(20),
    blocked_status BOOLEAN                DEFAULT FALSE,
    resources_link VARCHAR(200)
);