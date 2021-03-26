ALTER TABLE users_settings
    ADD FOREIGN KEY (id) REFERENCES users (id);