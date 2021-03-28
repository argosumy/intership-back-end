INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, image_url)
VALUES ('Ivan', 'Ivanov', 'Smila', 'ivan@spd-ukraine.com', 'developer', '+380685489524', 'users/1/homer3.jpg');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, image_url)
VALUES ('Petua', 'Petrov', 'Kyiv', 'petua@spd-ukraine.com', 'developer', '+380676548921', 'users/1/homer3.jpg');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, image_url)
VALUES ('Gena', 'Genov', 'Charkiv', 'gena@spd-ukraine.com', 'developer', '+380685686243', 'users/1/homer3.jpg');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, image_url)
VALUES ('Vasua', 'Ivanov', 'Odessa', 'vasua@spd-ukraine.com', 'developer', '+38068658958', 'users/1/homer3.jpg');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'vk', 'vk.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'telegramm', 'teegramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'instagramm', 'instagramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'vk', 'vk.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'telegramm', 'teegramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'instagramm', 'instagramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'vk', 'vk.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'telegramm', 'teegramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'instagramm', 'instagramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'vk', 'vk.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'telegramm', 'teegramm.com');
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'instagramm', 'instagramm.com');

INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('1', 'profam garash', 'horoshiy garash', 'nedvizimost', '999', 'UAH', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220');
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('2', 'mobilnick', 'Iphone 20+Pro Max Ultra Super', 'electronica', '5', 'UAH', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220');
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('3', 'Gaming PC', 'my old PC', 'electronica', '720', 'USD', 'Cherkassy', 'ACTIVE', '2021-03-18T07:26:10.717220',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220');
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('3', 'Playstation 4', 'new generation console', 'electronica', '230', 'EUR', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220');