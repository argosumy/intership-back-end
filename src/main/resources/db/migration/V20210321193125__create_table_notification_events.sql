CREATE TABLE events(id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR (100) NOT NULL
);

INSERT INTO events (name) values ('NEW_ADVERTISEMENT');
INSERT INTO events (name) values ('ACCOUNT_BAN');
INSERT INTO events (name) values ('ADVERTISEMENT_BLOCK,');
INSERT INTO events (name) values ('NEW_COMMENTS_ADVERTISEMENT');
INSERT INTO events (name) values ('CHANGES_ADVERTISEMENT');
INSERT INTO events (name) values ('NEW_COMMENTS_TO_MY_COMMENTS');
INSERT INTO events (name) values ('NEW_MESSAGE_DIRECT');