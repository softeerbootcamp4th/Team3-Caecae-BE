INSERT INTO user (phone)
VALUES ('01000000001'),
       ('01000000002'),
       ('01000000003'),
       ('01000000004'),
       ('01000000005'),
       ('01000000006'),
       ('01000000007'),
       ('01000000008'),
       ('01000000009'),
       ('01000000010');

INSERT INTO racing_game_participant (user_id, distance, selection)
VALUES (1, 10.5, NULL),
       (2, 15.0, NULL),
       (3, 12.3, NULL),
       (4, 8.8, NULL),
       (5, 20.0, NULL),
       (6, 14.2, NULL),
       (7, 16.7, NULL),
       (8, 19.5, NULL),
       (9, 11.0, NULL),
       (10, 9.9, NULL);

-- 필요하면 로컬에서 사용