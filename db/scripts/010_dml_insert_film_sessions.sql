insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values  (6, 1, timestamp '2023-09-24 10:00', timestamp '2023-09-24 12:00', 300),
        (3, 1, timestamp '2023-09-24 13:00', timestamp '2023-09-24 15:00', 300),

        (4, 1, timestamp '2023-09-24 16:00', timestamp '2023-09-24 18:00', 450),
        (1, 2, timestamp '2023-09-24 19:00', timestamp '2023-09-24 22:00', 450),

        (6, 1, timestamp '2023-09-25 10:00', timestamp '2023-09-25 12:00', 300),
        (2, 2, timestamp '2023-09-25 13:00', timestamp '2023-09-25 16:00', 450),

        (6, 1, timestamp '2023-09-25 13:00', timestamp '2023-09-25 15:00', 300),
        (5, 2, timestamp '2023-09-25 16:00', timestamp '2023-09-25 19:00', 400),
        (1, 2, timestamp '2023-09-25 20:00', timestamp '2023-09-25 23:00', 450);