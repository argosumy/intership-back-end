CREATE TABLE users_settings
(
    id                                  SERIAL PRIMARY KEY NOT NULL,
    user_id                             int                NOT NULL,
    new_ads_notification                boolean            NOT NULL,
    new_comments_to_my_ad_notification  boolean            NOT NULL,
    replies_to_my_comments_notification boolean            NOT NULL,
    mentions_in_thread_notification     boolean            NOT NULL,
    wishlist_update_notification        boolean            NOT NULL,
    open_ads_in_new_tab                 boolean            NOT NULL
);