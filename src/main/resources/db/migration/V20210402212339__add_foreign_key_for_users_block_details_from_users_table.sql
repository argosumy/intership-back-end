ALTER TABLE users_block_details
    ADD FOREIGN KEY (user_id) REFERENCES users (id);