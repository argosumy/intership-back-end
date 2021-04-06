CREATE TABLE wish_list
(
    user_id           INT  NOT NULL REFERENCES users (id),
    advertisements_id INT  NOT NULL REFERENCES advertisements (id)
);