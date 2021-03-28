ALTER TABLE users_settings
    ADD FOREIGN KEY (user_id) REFERENCES users (id);