ALTER TABLE IF EXISTS users
    ADD COLUMN avatar VARCHAR(200),
    ALTER COLUMN is_blocked SET NOT NULL;

ALTER TABLE IF EXISTS users
    RENAME COLUMN e_mail TO email;
