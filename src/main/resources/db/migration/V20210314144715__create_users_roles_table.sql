CREATE TABLE users_roles
(
    user_id       int                NOT NULL,
    role          VARCHAR(50)        DEFAULT 'USER',
    PRIMARY KEY(user_id, role),
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
);
