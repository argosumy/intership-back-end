CREATE TABLE notifications
(
    id               SERIAL PRIMARY KEY NOT NULL,
    user_mail_to_id  INT                NOT NULL,
    advertisement_id INT,
    event_type       VARCHAR            NOT NULL,
    send_date        TIMESTAMP          NOT NULL
);
