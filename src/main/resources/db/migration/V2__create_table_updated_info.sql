CREATE TABLE advertisements
(
    id                 SERIAL PRIMARY KEY NOT NULL,
    status             VARCHAR(20)        NOT NULL,
    status_change_date TIMESTAMP          NOT NULL
);