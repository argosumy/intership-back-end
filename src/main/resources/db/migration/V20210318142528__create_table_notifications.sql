CREATE TABLE IF NOT EXISTS notifications(id SERIAL PRIMARY KEY NOT NULL,
description TEXT,
recipient INT,
writer INT,
advertisement INT,
status INT,
event INT,
date TIMESTAMP );
