ALTER TABLE comments
    ADD FOREIGN KEY (advertisement_id) REFERENCES advertisements (id);