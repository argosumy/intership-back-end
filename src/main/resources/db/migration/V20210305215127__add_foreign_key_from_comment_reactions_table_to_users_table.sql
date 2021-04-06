ALTER TABLE comment_reactions
    ADD FOREIGN KEY (user_id) REFERENCES users (id);