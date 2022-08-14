insert into users_info(id, city, clothing_size, date_of_birth, facebook_link, hobby, important_note, instagram_link,phone_number, shoe_size, telegram_link, vk_link)
values (1, 'California', 'XXL', '2004-12-12', 'facebook.com', 'playing pubg', 'I love eat', 'instagram.com','0777 345 342', 34, 'telegram.com', 'vk.com'),
       (2, 'Texas', 'XS', '1999-05-15', 'facebook.com', 'music', 'I love soccer', 'instagram.com', '0777 345 879', 42, 'telegram.com', 'vk.com');

insert into users(id, email, first_name, is_block, is_friend, last_name, password, photo, role, user_info_id)
VALUES (1, 'admin@gmail.com', 'Admin', true, false, 'string','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba', 'photo', 'ADMIN', 1),
       (2, 'user@gmail.com', 'USER', true, false, 'string','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW','photo', 'USER', 2);

insert into categories(id, name)
values (1, 'Электроника'),(2,'Одежда'),(3,'Школа'),(4,'Дом и сад'),(5,'Обувь'),(6,'Транспорт');

insert into sub_categories(id, name, category_id)
values (1, 'Смартфоны и телефоны', 1),(2,'Аудиотехника',1),(3,'Фото и видеокамеры',1),
       (4,'Автоэлектроника',1),(5,'ТВ и видео',1),(6,'Компьютеры, ноутбуки и планшеты',1),
       (7,'Мужская одежда',2),(8,'Женская одежда',2),(9,'Детская одежда',2),
       (10,'Сумки',2),(11,'Часы',2),(12,'Аксессуары',2),
       (13,'Школьные канцтовары',3),(14,'Рюкзаки и сумки',3),(15,'Школьно-письменные принадлежности',3),
       (16,'Альбомы и папки',3),(17,'Мебель для школы',3),(18,'Пеналы',3),
       (19,'Мебель',4),(20,'Текстиль',4),(21,'Сантехника',4),
       (22,'Инструмент',4),(23,'Интерьер и Декор',4),(24,'Освещение',4),
       (25,'Женская обувь',5),(26,'Мужская обувь',5),(27,'Детская обувь',5),
       (28,'Спортивная обувь',5),(29,'Сезонная обувь',5),(30,'Стельки и другие аксессуары',5),
       (31,'Автомобильная акустика',6),(32,'Автоэлектроника',6),(33,'Автозапчасти',6),
       (34,'Шины',6),(35,'Автомобильные аксессуары',6);
