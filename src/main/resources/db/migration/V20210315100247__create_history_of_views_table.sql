CREATE TABLE history_of_views
(
    id                SERIAL PRIMARY KEY NOT NULL,
    user_id           INT                NOT NULL REFERENCES users (id),
    advertisements_id INT                NOT NULL REFERENCES advertisements (id),
    viewed_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);