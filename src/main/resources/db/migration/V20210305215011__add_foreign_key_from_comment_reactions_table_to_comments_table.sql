ALTER TABLE comment_reactions
    ADD FOREIGN KEY (comment_id) REFERENCES comments (id)
        on delete cascade;