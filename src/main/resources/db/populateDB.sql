DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  (TIMESTAMP '2015-05-30 10:00:00.000','Завтрак','500', '100000'),
  (TIMESTAMP '2015-05-30 13:15:00.000','Обед','1000', '100000'),
  (TIMESTAMP '2015-05-30 17:29:10.000','Ужин','500', '100000'),
  (TIMESTAMP '2015-05-30 09:55:00.000','Завтрак','600', '100001'),
  (TIMESTAMP '2015-05-30 14:10:00.000','Обед','950', '100001'),
  (TIMESTAMP '2015-05-30 18:40:01.000','Ужин','500', '100001');