ALTER TABLE users
    ADD CONSTRAINT constraint_unique_email UNIQUE (e_mail);
