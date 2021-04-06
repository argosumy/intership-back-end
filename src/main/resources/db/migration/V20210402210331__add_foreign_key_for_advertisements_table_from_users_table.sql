ALTER TABLE advertisements
    ADD FOREIGN KEY (user_id) REFERENCES users (id);