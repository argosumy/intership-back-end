ALTER TABLE notifications
    ADD FOREIGN KEY (advertisement_id) REFERENCES advertisements (id);