ALTER TABLE comments
    ADD FOREIGN KEY (user_id) REFERENCES users (id);