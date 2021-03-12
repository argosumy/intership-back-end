ALTER TABLE users_additional_resources
    ADD FOREIGN KEY (user_id) REFERENCES users (id);