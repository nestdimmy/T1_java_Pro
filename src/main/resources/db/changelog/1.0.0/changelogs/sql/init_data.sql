INSERT INTO department(id, name)
VALUES
    (1, 'Деп'),
    (2, 'Деп2'),
    (3, 'Деп3'),
    (4, 'Деп4');

INSERT INTO users(username, department_id) VALUES
                            ('Дима', 1),
                            ('Паша', 1),
                            ('Вася', 2),
                            ('Женя', 3),
                            ('User', 3),
                            ('TestUser', 4),
                            ('DataUser', 4);