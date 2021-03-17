CREATE TABLE history_of_views
(
    id                SERIAL PRIMARY KEY NOT NULL,
    user_id           INT                NOT NULL,
    advertisements_id INT                NOT NULL,
    viewed_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);