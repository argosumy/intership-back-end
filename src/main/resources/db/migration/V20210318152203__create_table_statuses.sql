CREATE TABLE statuses(id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR (100) NOT NULL
);

INSERT INTO statuses (id, name) values (1,'NEW');
INSERT INTO statuses (id, name) values (2,'SEND');

