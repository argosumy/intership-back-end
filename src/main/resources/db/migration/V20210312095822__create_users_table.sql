CREATE TABLE users
(
    id                           SERIAL PRIMARY KEY NOT NULL,
    first_name                   VARCHAR(100)       NOT NULL,
    last_name                    VARCHAR(100)       NOT NULL,
    e_mail                       VARCHAR(100)       NOT NULL,
    position                     VARCHAR(50)        NOT NULL,
    phone_number                 VARCHAR(20)        NOT NULL,
    is_blocked                   BOOLEAN DEFAULT false,
    additional_contact_resources VARCHAR[]          NOT NULL
);