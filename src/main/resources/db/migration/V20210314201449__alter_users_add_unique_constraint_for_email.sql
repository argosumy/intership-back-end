ALTER TABLE users
    DROP CONSTRAINT IF EXISTS constraint_unique_email,
    ADD CONSTRAINT constraint_unique_email UNIQUE (e_mail);
