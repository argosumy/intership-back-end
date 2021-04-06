ALTER TABLE comments
    ADD FOREIGN KEY (parent_id) REFERENCES comments (id)
        on delete cascade;