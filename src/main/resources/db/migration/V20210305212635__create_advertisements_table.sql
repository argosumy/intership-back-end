CREATE TABLE advertisements
(
    id                    SERIAL PRIMARY KEY NOT NULL,
    user_id               int                NOT NULL,
    title                 VARCHAR(200),
    description           TEXT,
    category              VARCHAR,
    price                 numeric CHECK ( price > 0),
    currency              VARCHAR(3),
    discount_availability BOOLEAN DEFAULT false,
    city                  VARCHAR(20),
    status                VARCHAR(15)        NOT NULL,
    creation_date         TIMESTAMP          NOT NULL,
    publication_date      TIMESTAMP,
    status_change_date    TIMESTAMP          NOT NULL
);