ALTER TABLE comment_likes
    ADD FOREIGN KEY (user_id) REFERENCES users (id);