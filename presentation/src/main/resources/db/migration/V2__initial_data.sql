INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');

INSERT INTO users (username, password) VALUES ('marcelo', '$2a$10$pVKXigIzE6YINjYzgs5wQOJndKk7ApQwJbgp5esY9w7sMfjEOM08i');
INSERT INTO users (username, password) VALUES ('eldick', '$2a$10$pVKXigIzE6YINjYzgs5wQOJndKk7ApQwJbgp5esY9w7sMfjEOM08i');

INSERT INTO owner (name, birth_date, user_id, username) VALUES ('mark', '2004-01-01', 1, 'marcelo');
INSERT INTO owner (name, birth_date, user_id, username) VALUES ('eldar', '2003-01-01', 2, 'eldick');

INSERT INTO cat (name, breed, owner_id, color, birth_date) VALUES ('lite', 'durak', 1, 'orange_white', '2023-01-01');
INSERT INTO cat (name, breed, owner_id, color, birth_date) VALUES ('brus', 'mein kun', 2, 'black', '2024-01-01');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO cat_friend (cat_id, cat_friend_id) VALUES (1, 2);
INSERT INTO cat_friend (cat_id, cat_friend_id) VALUES (2, 1);
