INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, avatar)
VALUES ('Ivan', 'Ivanov', 'Smila', 'ivan@spd-ukraine.com', 'developer', '+380685489524', 'users/1/homer3.jpg') ON CONFLICT DO NOTHING;
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, avatar)
VALUES ('Petua', 'Petrov', 'Kyiv', 'petua@spd-ukraine.com', 'developer', '+380676548921', 'users/1/homer3.jpg') ON CONFLICT DO NOTHING;
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, avatar)
VALUES ('Gena', 'Genov', 'Charkiv', 'gena@spd-ukraine.com', 'developer', '+380685686243', 'users/1/homer3.jpg') ON CONFLICT DO NOTHING;
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number, avatar)
VALUES ('Vasua', 'Ivanov', 'Odessa', 'vasua@spd-ukraine.com', 'developer', '+38068658958', 'users/1/homer3.jpg') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'vk', 'vk.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'telegramm', 'teegramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (1, 'instagramm', 'instagramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'vk', 'vk.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'telegramm', 'teegramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (2, 'instagramm', 'instagramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'vk', 'vk.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'telegramm', 'teegramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (3, 'instagramm', 'instagramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'vk', 'vk.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'telegramm', 'teegramm.com') ON CONFLICT DO NOTHING;
INSERT INTO users_additional_resources (user_id, resource_name, resource_url)
VALUES (4, 'instagramm', 'instagramm.com') ON CONFLICT DO NOTHING;

INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('1', 'profam garash', 'horoshiy garash', 'nedvizimost', '999', 'UAH', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220') ON CONFLICT DO NOTHING;
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('2', 'mobilnick', 'Iphone 20+Pro Max Ultra Super', 'electronica', '5', 'UAH', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220') ON CONFLICT DO NOTHING;
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('3', 'Gaming PC', 'my old PC', 'electronica', '720', 'USD', 'Cherkassy', 'ACTIVE', '2021-03-18T07:26:10.717220',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220') ON CONFLICT DO NOTHING;
INSERT INTO advertisements(user_id, title, description, category, price, currency, city, status, creation_date,
                           publication_date, status_change_date)
VALUES ('3', 'Playstation 4', 'new generation console', 'electronica', '230', 'EUR', 'Cherkassy', 'ACTIVE',
        '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220', '2021-03-18T07:26:10.717220') ON CONFLICT DO NOTHING;

INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (1, 'true', 'true', 'true', 'true', 'true', 'true') ON CONFLICT DO NOTHING;
INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (2, 'false', 'false', 'false', 'false', 'false', 'false') ON CONFLICT DO NOTHING;
INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (3, 'true', 'false', 'true', 'false', 'true', 'false') ON CONFLICT DO NOTHING;

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

INSERT INTO characteristics (advertisement_id, category_name, characteristics_name, characteristics_value, is_approved)
VALUES (1, 'OTHER', 'color', 'red', TRUE);
INSERT INTO characteristics (advertisement_id, category_name, characteristics_name, characteristics_value, is_approved)
VALUES (1, 'OTHER', 'length', '123', TRUE);
INSERT INTO characteristics (advertisement_id, category_name, characteristics_name, characteristics_value, is_approved)
VALUES (1, 'OTHER', 'OS', 'WINDOWS 10', FALSE);
INSERT INTO characteristics (advertisement_id, category_name, characteristics_name, characteristics_value, is_approved)
VALUES (2, 'ANIMALS', 'weight', '15', TRUE);
INSERT INTO characteristics (advertisement_id, category_name, characteristics_name, characteristics_value, is_approved)
VALUES (2, 'ANIMALS', 'color', 'white', FALSE);

INSERT INTO wish_list(user_id, advertisement_id)
VALUES (1, 2);
INSERT INTO wish_list(user_id, advertisement_id)
VALUES (1, 3);

INSERT INTO history_of_views(user_id, advertisements_id) VALUES (1, 2);
INSERT INTO history_of_views(user_id, advertisements_id) VALUES (1, 1);
INSERT INTO history_of_views(user_id, advertisements_id) VALUES (1, 3);
INSERT INTO history_of_views(user_id, advertisements_id) VALUES (2, 2);
INSERT INTO history_of_views(user_id, advertisements_id) VALUES (2, 3);
INSERT INTO history_of_views(user_id, advertisements_id) VALUES (1, 3);
