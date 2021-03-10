CREATE TABLE advertisements
(
    id                    SERIAL PRIMARY KEY         NOT NULL,
    user_id               int                        NOT NULL,
    title                 VARCHAR(200)               NOT NULL,
    description           TEXT                       NOT NULL,
    category              VARCHAR                    NOT NULL,
    price                 numeric CHECK ( price > 0) NOT NULL,
    currency              VARCHAR(3)                 NOT NULL,
    is_discount_available BOOLEAN DEFAULT false,
    city                  VARCHAR(20)                NOT NULL,
    status                VARCHAR(15)                NOT NULL,
    creation_date         TIMESTAMP                  NOT NULL,
    publication_date      TIMESTAMP                  NOT NULL,
    status_change_date    TIMESTAMP                  NOT NULL
);