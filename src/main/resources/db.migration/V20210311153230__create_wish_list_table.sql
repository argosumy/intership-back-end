CREATE TABLE wish_list
(
    user_id           INT REFERENCES users (id) NOT NULL,
    advertisements_id INT                       NOT NULL,
);