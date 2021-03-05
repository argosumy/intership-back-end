ALTER TABLE comment_likes
    ADD FOREIGN KEY (comment_id) REFERENCES comments (id);