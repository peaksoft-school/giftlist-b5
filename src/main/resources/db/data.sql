insert into users_info(id, city,clothing_size, date_of_birth, facebook_link, hobby, important_note, instagram_link, phone_number, shoe_size, telegram_link, vk_link)
values (1, 'California', 'XXL', '2004-12-12', 'facebook.com', 'playing pubg', 'I love eat', 'instagram.com','0777 345 342', 34, 'telegram.com', 'vk.com'),
       (2, 'Texas', 'XS', '1999-05-15', 'facebook.com', 'music', 'I love soccer', 'instagram.com', '0777 345 879', 42, 'telegram.com', 'vk.com'),
       (3, 'Issyk-Kul', 'XL', '1990-05-10', 'facebook.com', 'music', 'I love guitar', 'instagram.com', '0777 005 399', 40, 'telegram.com', 'vk.com'),
       (4, 'Batken', 'L', '1999-10-15', 'facebook.com', 'music', 'I love music', 'instagram.com', '0777 044 345', 42, 'telegram.com', 'vk.com'),
       (5, 'Jalal-Abad', 'M', '1999-02-15', 'facebook.com', 'music', 'I love PUBG', 'instagram.com', '0777 935 134', 42, 'telegram.com', 'vk.com');

insert into users(id, email, first_name, is_block, last_name, password, photo,role, user_info_id)
VALUES (1, 'admin@gmail.com', 'Admin', false , 'string','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba','photo', 'ADMIN', 1),
       (2, 'user@gmail.com', 'USER', false , 'string','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW','photo', 'USER', 2),
       (3, 'seit@gmail.com', 'Сеитбек', false , 'Нарынбаев','$2a$12$0biFEVvYS8vuacj1xQT0k.L0sxgAfmgOlLiFDo77cGFxE8l6RLgau','photo', 'USER', 3),
       (4, 'kunzaada@gmail.com', 'Кунзаада', false , 'Бекжанова','$2a$12$c3erzd4PElusy1mI.18YFePnZp2Em5quAHxmF/MoHHt4AKu9k8rum','photo', 'USER', 4),
       (5, 'elmirbek@gmail.com', 'Элмирбек', false , 'Алишеров','$2a$12$QQY9Efc7tiLfFuRBgM5Cz..DCsrSFWljmUSZyaEBn1fhVoMc7TV6O','photo', 'USER', 5);

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

insert into holidays(id, holiday_date, is_block, name, photo, user_id)
VALUES (1,'2022-03-08',false,'8-март','https://giftlist-bucket.s3.amazonaws.com/1661168068897march-8-pink-flowers-wallpaper-preview.jpg',2),
       (2,'2022-12-31',false,'Новый год','https://giftlist-bucket.s3.amazonaws.com/1661168450017newyear-1173474360-612x612.jpg',2),
       (3,'2022-03-21',false,'Нооруз','https://giftlist-bucket.s3.amazonaws.com/1661168510692nooruz.jpg',2);


insert into wishes(id,created_at,description,gift_link,gift_name,gift_photo,is_block,is_hidden,wish_date,booking_id,from_user_id, holidays,user_id)
values (1,'2022-08-22','Жакет с однобортной застежкой на пуговицу выполнен в двух цветах: черном и синем','https://loverepublic.ru/catalog/odezhda/zhakety/','ЖАКЕТ','https://giftlist-bucket.s3.amazonaws.com/1661168510692nooruz.jpg',false,false,'2022-09-01',null,null,1,2),
       (2,'2022-08-23','Bluetooth 5.0, тип зарядки кейса: Lightning, беспроводная зарядка, степень защиты: IPX4, время работы: 6 ч, время работы от аккумулятора в кейсе: 30 ч, вес: 42.19 г','https://www.kivano.kg/product/view/besprovodnye-naushniki-apple-airpods-3','Apple AirPods 3','https://giftlist-bucket.s3.amazonaws.com/1661168510692nooruz.jpg',false,false,'2022-12-31',null,null,2,2),
       (3,'2022-08-23','Серьги из серебра Opal C3292 шампань циркон 925 проба','https://svetofor.info/sergi-iz-serebra-opal-c3292-shampan-cirkon-925-proba-87.html','Серьги','https://giftlist-bucket.s3.amazonaws.com/1661168510692nooruz.jpg',false,false,'2023-03-21',null,null,3,2);

insert into gifts(id, created_at, description, is_block, name, photo, status, booking_id, category_id, from_user_id, user_id)
VALUES(1,'2022-08-23','Гордость и предубеждение, Джейн Остен',false,'Книга','photo-link','USED',null,3,null,2),
      (2,'2022-08-23','Гитара классическая Yamaha C40',false,'Гитара','photo-link','USED',null,1,null,2),
      (3,'2022-08-23','Детская обувь Levi''s Kids',false,'Обувь','photo-link','NEW',null,5,null,2);

insert into friends
VALUES (3,4),
       (3,5),
       (4,2),
       (4,5);