CREATE TABLE comment_reactions
(
    id         SERIAL PRIMARY KEY NOT NULL,
    reaction   VARCHAR(10)        NOT NULL,
    user_id    INT                NOT NULL,
    comment_id INT                NOT NULL
);