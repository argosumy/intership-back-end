ALTER TABLE users
    ADD COLUMN avatar VARCHAR(200);

ALTER TABLE users
    RENAME COLUMN e_mail TO email;
