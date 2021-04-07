ALTER TABLE notifications
    ADD FOREIGN KEY (user_mail_to_id) REFERENCES users (id);