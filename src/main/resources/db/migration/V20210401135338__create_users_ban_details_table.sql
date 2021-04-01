CREATE TABLE users_ban_details
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     int                NOT NULL,
    baned_until TIMESTAMP          NOT NULL,
    reason      VARCHAR(200)       NOT NULL,
    is_notify   BOOLEAN DEFAULT false
);