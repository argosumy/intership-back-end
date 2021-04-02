CREATE TABLE users_block_details
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     int                NOT NULL,
    baned_until TIMESTAMP,
    reason      VARCHAR(200),
    is_notify   BOOLEAN DEFAULT false,
    is_blocked  BOOLEAN            NOT NULL
);