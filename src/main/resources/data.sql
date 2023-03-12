-- Add users and roles
INSERT INTO ROLE(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO T_USER(firstname, lastname, username, created_at, updated_at, enabled, password)
VALUES ('Ratmir', 'Dudin', 'ratmirdudin', current_timestamp, current_timestamp, TRUE,
        '$2a$12$IZguxm.e.4R2.zf9MaEyY.i1LVi.KUpApj3NomvROcr2YVXH06vZq'),
       ('Vladimir', 'Dudin', 'vladimirdudin', current_timestamp, current_timestamp, TRUE,
        '$2a$12$x6sXNFyrFWuLZm3zdDUo.O54RgEXnDUoCmPH5HvN4jNW6UWvV1AsG'),
       ('Dima', 'Labanav', 'dimalabanav', current_timestamp, current_timestamp, TRUE,
        '$2a$12$zrmNOldZ9lKn8yr/EH63GOLaloLWdPfOEVGU0ZMTHxKU6OMyH.hZC');

INSERT INTO USER_ROLE(user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1);

-- Add foods and tags
INSERT INTO TAG(name)
VALUES ('Asian kitchen'),
       ('Korean kitchen'),
       ('Japan kitchen'),
       ('Chinese kitchen'),
       ('Russian kitchen'),
       ('Caucasian kitchen');

INSERT INTO FOOD(name, description, created_at, updated_at)
VALUES ('Гречка с курицей', 'Описание блюда', current_timestamp, current_timestamp),
       ('Салат цезарь с курицей и сухариками', 'Описание блюда', current_timestamp, current_timestamp),
       ('Торт "Черепаха', 'Описание блюда', current_timestamp, current_timestamp),
       ('Бурито', 'Описание блюда', current_timestamp, current_timestamp),
       ('Салат "Мимоза" классический', 'Описание блюда', current_timestamp, current_timestamp),
       ('Пасхальный кулич', 'Описание блюда', current_timestamp, current_timestamp),
       ('Салат "Цезарь" классический', 'Описание блюда', current_timestamp, current_timestamp),
       ('Салат "Хрустящий', 'Описание блюда', current_timestamp, current_timestamp),
       ('Пасха из творога', 'Описание блюда', current_timestamp, current_timestamp),
       ('Кексы с изюмом', 'Описание блюда', current_timestamp, current_timestamp),
       ('Мясо по-французски с картофелем', 'Описание блюда', current_timestamp, current_timestamp),
       ('Мисо суп', 'Описание блюда', current_timestamp, current_timestamp),
       ('Салат из кальмаров', 'Описание блюда', current_timestamp, current_timestamp),
       ('Чизкейк творожный', 'Описание блюда', current_timestamp, current_timestamp),
       ('Торт "Панчо"', 'Описание блюда', current_timestamp, current_timestamp),
       ('Сливочный грибной суп', 'Описание блюда', current_timestamp, current_timestamp),
       ('Котлеты "Гнезда"', 'Описание блюда', current_timestamp, current_timestamp),
       ('Запеканка из картофеля и мясного фарша', 'Описание блюда', current_timestamp, current_timestamp);

INSERT INTO FOOD_TAG(food_id, tag_id)
VALUES (1, 1),
       (2, 3),
--        (2, 6),
--        (2, 4),
       (3, 5),
       (3, 6),
       (4, 3),
       (4, 5),
       (5, 2),
       (6, 6),
       (6, 1),
--        (7, 2),
--        (7, 3),
--        (8, 4),
       (8, 1),
       (8, 3),
       (9, 6),
       (10, 3),
       (10, 5),
       (11, 4),
       (11, 5),
       (11, 6),
--        (12, 1),
--        (12, 2),
       (13, 5),
       (14, 1),
       (14, 3),
       (15, 2),
       (15, 3),
       (16, 2),
       (16, 3),
       (16, 5),
       (17, 6),
       (17, 1),
       (18, 1),
       (18, 2);