-- these commands remove all tables from the database
-- netstat -ano | findstr 8080
-- it implies an error if tables not exist in DB, just ignore it
-- DROP table if exists orders_items;
-- DROP table if exists tariffs;
-- DROP table if exists orders;
-- DROP table if exists categories;
-- DROP table if exists statuses;
-- DROP table if exists users;
-- DROP table if exists roles;

-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(10) UNIQUE NOT NULL
);

INSERT INTO roles
VALUES (0, 'admin');
INSERT INTO roles
VALUES (1, 'client');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
CREATE TABLE users
(
    id            SERIAL PRIMARY KEY                                                 NOT NULL,
    role_id       INTEGER REFERENCES roles (id) ON DELETE CASCADE ON UPDATE RESTRICT NOT NULL,
    email         VARCHAR(128) UNIQUE                                                NOT NULL,
    password      VARCHAR(30)                                                        NOT NULL,
    account_state INTEGER                                                            NOT NULL DEFAULT 0,
    user_status   INTEGER                                                            NOT NULL DEFAULT 1
);

INSERT INTO users
VALUES (DEFAULT, 0, 'admin@takogo.net', 'adminpass', default, default);
INSERT INTO users
VALUES (DEFAULT, 1, 'client@takogo.net', 'clientpass', default, default);
INSERT INTO users
VALUES (DEFAULT, 1, 'петров@takogo.net', 'петров', default, default);


CREATE TABLE statuses
(
    id   INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(10) UNIQUE  NOT NULL
);

INSERT INTO statuses
VALUES (0, 'opened');
INSERT INTO statuses
VALUES (1, 'confirmed');
INSERT INTO statuses
VALUES (2, 'paid');
INSERT INTO statuses
VALUES (3, 'closed');


CREATE TABLE categories
(
    id            SERIAL PRIMARY KEY    NOT NULL,
    name          VARCHAR(60) UNIQUE    NOT NULL,
    url           CHARACTER VARYING(60) NOT NULL
);

INSERT INTO categories
VALUES (1, 'Internet', 'Internet-Url');
INSERT INTO categories
VALUES (2, 'Telephony', 'Telephony-Url');
INSERT INTO categories
VALUES (3, 'IP-TV', 'IP-TV-Url');
INSERT INTO categories
VALUES (4, 'Cable-TV', 'Cable-TV-Url');


-- --------------------------------------------------------------
-- ORDERS
-- --------------------------------------------------------------
CREATE TABLE orders
(
    id      SERIAL PRIMARY KEY            NOT NULL,
    user_id INTEGER REFERENCES users (id) NOT NULL,
    created TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT now() NOT NULL
);


CREATE TABLE tariffs
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(50)                        NOT NULL,
    description   TEXT                               NOT NULL,
    image_link    CHARACTER VARYING(255)             NOT NULL,
    price         NUMERIC                            NOT NULL,
    categories_id INTEGER REFERENCES categories (id) NOT NULL
);

-- Internet
INSERT INTO tariffs
VALUES (DEFAULT, 'HomeNet2', 'HomeNet2 Description', 'image_link', 175, 1); -- 1 (order id)
INSERT INTO tariffs
VALUES (DEFAULT, 'SpeedNet2', 'SpeedNet2 Description', 'image_link', 205, 1); -- 2
INSERT INTO tariffs
VALUES (DEFAULT, 'HighSpeedNet2', 'HighSpeedNet2 Description', 'image_link', 240, 1);
-- 3
-- Telephony
INSERT INTO tariffs
VALUES (DEFAULT, 'Home60_2', 'Home60_2 Description', 'image_link', 90, 2); -- 4
INSERT INTO tariffs
VALUES (DEFAULT, 'Home100_2', 'Home100_2 Description', 'image_link', 80, 2); -- 5
INSERT INTO tariffs
VALUES (DEFAULT, 'Home300_2', 'Home300_2 Description', 'image_link', 120, 2);
-- 6
-- IP-TV
INSERT INTO tariffs
VALUES (DEFAULT, 'IP-TV Basic_2', 'IP-TV Basic_2 Description', 'image_link', 270, 3); -- 7
INSERT INTO tariffs
VALUES (DEFAULT, 'IP-TV Advanced_2', 'IP-TV Advanced_2 Description', 'image_link', 220, 3);
-- 8
-- Cable TV
INSERT INTO tariffs
VALUES (DEFAULT, 'Standard TV_2', 'Standard TV_2 Description', 'image_link', 180, 4); -- 9
INSERT INTO tariffs
VALUES (DEFAULT, 'Digital TV_2', 'Digital TV_2 Description', 'image_link', 280, 4);



CREATE TABLE orders_items
(
    id        SERIAL PRIMARY KEY              NOT NULL,
    order_id  INTEGER REFERENCES orders (id)  NOT NULL,
    tariff_id INTEGER REFERENCES tariffs (id) NOT NULL
);

-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
-- SELECT *
-- FROM orders_items;
-- SELECT *
-- FROM tariffs;
-- SELECT *
-- FROM orders;
SELECT *
FROM categories;
-- SELECT *
-- FROM statuses;
-- SELECT *
-- FROM users;
-- SELECT *
-- FROM roles;
