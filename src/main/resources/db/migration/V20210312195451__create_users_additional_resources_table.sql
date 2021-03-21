CREATE TABLE IF NOT EXISTS users_additional_resources
(
    id            SERIAL PRIMARY KEY NOT NULL,
    user_id       int                NOT NULL,
    resource_name VARCHAR(50)        NOT NULL,
    resource_url  VARCHAR            NOT NULL
);