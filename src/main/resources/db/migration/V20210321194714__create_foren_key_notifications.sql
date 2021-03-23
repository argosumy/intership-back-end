ALTER TABLE notifications
    ADD FOREIGN KEY (status) REFERENCES statuses (id);

ALTER TABLE notifications
    ADD FOREIGN KEY (event) REFERENCES events (id);

ALTER TABLE notifications
    ADD FOREIGN KEY (recipient) REFERENCES users (id);

ALTER TABLE notifications
    ADD FOREIGN KEY (writer) REFERENCES users (id);