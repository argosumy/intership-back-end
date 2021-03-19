INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number)
VALUES ('Ivan', 'Ivanov', 'Smila', 'ivan@spd-ukraine.com', 'developer', '+380685489524');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number)
VALUES ('Petua', 'Petrov', 'Kyiv', 'petua@spd-ukraine.com', 'developer', '+380676548921');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number)
VALUES ('Gena', 'Genov', 'Charkiv', 'gena@spd-ukraine.com', 'developer', '+380685686243');
INSERT INTO users (first_name, last_name, location, e_mail, position, phone_number)
VALUES ('Vasua', 'Ivanov', 'Odessa', 'vasua@spd-ukraine.com', 'developer', '+38068658958');
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

INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (1, 'true', 'true', 'true', 'true', 'true', 'true');
INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (2, 'false', 'false', 'false', 'false', 'false', 'false');
INSERT INTO users_settings (user_id, new_ads_notification, new_comments_to_my_ad_notification,
                            replies_to_my_comments_notification, mentions_in_thread_notification,
                            wishlist_update_notification, open_ads_in_new_tab)
VALUES (3, 'true', 'false', 'true', 'false', 'true', 'false');