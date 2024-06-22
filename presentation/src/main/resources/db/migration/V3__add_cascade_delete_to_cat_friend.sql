ALTER TABLE cat_friend
DROP CONSTRAINT cat_friend_cat_1_fk;

ALTER TABLE cat_friend
    ADD CONSTRAINT cat_friend_cat_1_fk
        FOREIGN KEY (cat_id)
            REFERENCES cat (id)
            ON DELETE CASCADE;

ALTER TABLE cat_friend
DROP CONSTRAINT cat_friend_cat_2_fk;

ALTER TABLE cat_friend
    ADD CONSTRAINT cat_friend_cat_2_fk
        FOREIGN KEY (cat_friend_id)
            REFERENCES cat (id)
            ON DELETE CASCADE;
