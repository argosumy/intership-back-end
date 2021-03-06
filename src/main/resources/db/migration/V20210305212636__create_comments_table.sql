CREATE TABLE comments
(
    id               SERIAL PRIMARY KEY NOT NULL,
    body             TEXT               NOT NULL,
    created_at       TIMESTAMP          NOT NULL,
    advertisement_id INT                NOT NULL,
    user_id          INT                NOT NULL,
    parent_id        INT
);