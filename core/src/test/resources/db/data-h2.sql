DELETE
FROM user;

INSERT INTO user (id, name, age, email, tenant_id)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', 1),
       (2, 'Jack', 20, 'test2@baomidou.com', 2),
       (3, 'Tom', 28, 'test3@baomidou.com', 3),
       (4, 'Sandy', 21, 'test4@baomidou.com', 4),
       (5, 'Billie', 24, 'test5@baomidou.com', 5);