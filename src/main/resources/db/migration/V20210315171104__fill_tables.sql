INSERT INTO advertisements (user_id, title, description, category, price, currency, discount_availability, city, status,
                            creation_date, publication_date, status_change_date)
VALUES (1, 'title1', 'description1', 'category1', 25, 'UAH', true, 'city1', 'DRAFT', '2019-03-12T22:54:28.000000',
        '2022-03-12T22:54:35.000000', '2021-03-12T22:54:37.000000');
INSERT INTO advertisements (user_id, title, description, category, price, currency, discount_availability, city, status,
                            creation_date, publication_date, status_change_date)
VALUES (1, 'title2', 'description2', 'category2', 100, 'USD', true, 'city2', 'ACTIVE', '2019-03-12T22:54:28.000000',
        '2020-03-12T22:54:35.000000', '2021-03-12T22:54:37.000000');
INSERT INTO advertisements (user_id, title, description, category, price, currency, discount_availability, city, status,
                            creation_date, publication_date, status_change_date)
VALUES (2, 'title3', 'description3', 'category3', 100500, 'EUR', true, 'city3', 'ACTIVE',
        '1999-03-12T22:54:28.000000',
        '2000-03-12T22:54:35.000000', '2021-03-12T22:54:37.000000');
INSERT INTO advertisements (user_id, title, description, category, price, currency, discount_availability, city, status,
                            creation_date, publication_date, status_change_date)
VALUES (1, 'title4', 'description4', 'category4', 1, 'UAH', false, 'city4', 'DRAFT', '2021-03-12T22:54:28.000000',
        '2022-03-12T22:54:35.000000', '2021-03-12t22:54:37.000000');
INSERT INTO advertisements (user_id, title, description, category, price, currency, discount_availability, city, status,
                            creation_date, publication_date, status_change_date)
VALUES (2, 'title5', 'description5', 'category5', 17, 'UAH', false, 'city5', 'DRAFT', '2021-03-12T22:54:28.000000',
        '2018-03-12T22:54:35.000000', '2021-03-12T22:54:37.000000');

INSERT INTO users (first_name, last_name, location, email, position, phone_number)
VALUES ('firstname1', 'lastname2', 'location1', 'test1@mail.com', 'position1', '+123456789001');
INSERT INTO users (first_name, last_name, location, email, position, phone_number)
VALUES ('firstname2', 'lastname2', 'location2', 'test2@mail.com', 'position2', '+123456789002');
INSERT INTO users (first_name, last_name, location, email, position, phone_number)
VALUES ('firstname3', 'lastname3', 'location3', 'test3@mail.com', 'position3', '+123456789003');

INSERT INTO comments (id, body, created_at, advertisement_id, user_id, parent_id)
VALUES (0, ' ', '2000-01-01 00:00:00.000000', 1, 1, NULL);
INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id)
VALUES ('some comment2', '2021-03-12 00:00:00.000000', 1, 1, NULL);
INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id)
VALUES ('some comment3', '1888-03-12 00:00:00.000000', 2, 2, 1);
INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id)
VALUES ('some comment4', '1999-03-12 00:00:00.000000', 1, 1, 1);
INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id)
VALUES ('some comment5', '2000-03-12 00:00:00.000000', 1, 2, 2);

INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('LIKE', 1, 1);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('LIKE', 1, 2);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('LIKE', 1, 3);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('DISLIKE', 2, 1);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('LIKE', 2, 3);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('DISLIKE', 2, 2);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('LIKE', 3, 3);
INSERT INTO comment_reactions (reaction, user_id, comment_id) VALUES ('DISLIKE', 2, 3);

