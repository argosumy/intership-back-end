CREATE TABLE "users"
(
    "id"             SERIAL PRIMARY KEY NOT NULL,
    "avatar"         VARCHAR(255)       NOT NULL,
    "first_name"     VARCHAR(50)        NOT NULL,
    "last_name"      VARCHAR(50)        NOT NULL,
    "email"          VARCHAR(50)        NOT NULL,
    "position"       VARCHAR(30)        NOT NULL,
    "phone_number"   VARCHAR(15)        NOT NULL,
    "blocked_status" BOOLEAN,
    "resources_link" VARCHAR(255)       NOT NULL
);