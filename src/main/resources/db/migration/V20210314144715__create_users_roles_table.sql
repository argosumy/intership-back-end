CREATE TABLE IF NOT EXISTS users_roles
(
    user_id       int                NOT NULL,
    role          VARCHAR(50)        NOT NULL,
    PRIMARY KEY(user_id, role),
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);
