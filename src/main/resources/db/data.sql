insert into users_info(id, city,clothing_size, date_of_birth, facebook_link, hobby, important_note, instagram_link, phone_number, shoe_size, telegram_link, vk_link)
values (1, 'California', 'XXL', '2004-12-12', 'facebook.com', 'playing pubg', 'I love eat', 'https://www.instagram.com','0777 345 342', 34, 'https://www.telegram.com', 'https://www.vk.com'),
       (2, 'Texas', 'XS', '1999-05-15', 'facebook.com', 'music', 'I love soccer', null, '0777 345 879', 42, 'https://www.telegram.com', 'https://www.vk.com'),
       (3, 'Issyk-Kul', 'XL', null, 'facebook.com', 'music', 'I love guitar', 'https://www.instagram.com', '0777 005 399', 40, 'https://www.telegram.com', 'https://www.vk.com'),
       (4, 'Batken', 'L', '1999-10-15', 'facebook.com', 'music', 'I love music', 'https://www.instagram.com', '0777 044 345', 42, 'https://www.telegram.com', 'https://www.vk.com'),
       (5, 'Jalal-Abad', 'M', '1999-02-15', 'facebook.com', 'music', 'I love PUBG', 'https://www.instagram.com', '0777 935 134', 42, 'telegram.com', 'https://www.vk.com'),
       (6, 'Naryn', 'S', '2004-02-15', 'facebook.com', 'music', 'I love PUBG', 'https://www.instagram.com', '0999 433 333', 39, 'https://www.telegram.com', 'https://www.vk.com');

insert into users(id, email, first_name, is_block, last_name, password, photo,role, user_info_id)
VALUES (1, 'admin@gmail.com', 'Steve', false , 'Jobs','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'ADMIN', 1),
       (2, 'user@gmail.com', 'Bill', false , 'Gates','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'USER', 2),
       (3, 'seit@gmail.com', 'Сейитбек', false , 'Нарынбаев','$2a$12$0biFEVvYS8vuacj1xQT0k.L0sxgAfmgOlLiFDo77cGFxE8l6RLgau','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'USER', 3),
       (4, 'kunzaada@gmail.com', 'Кунзаада', false , 'Бекжанова','$2a$12$c3erzd4PElusy1mI.18YFePnZp2Em5quAHxmF/MoHHt4AKu9k8rum','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'USER', 4),
       (5, 'elmirbekalisherov24@gmail.com', 'Элмирбек', false , 'Алишеров','$2a$12$QQY9Efc7tiLfFuRBgM5Cz..DCsrSFWljmUSZyaEBn1fhVoMc7TV6O','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'USER', 5),
       (6, 'dinara@gmail.com', 'Dinara', false , 'Rahatbek kyzy','$2a$10$iOruTCPtGMtkbC5P2Wo0ne/.KBGmHIrQ.kOYTKIMKrp1k.3iYNjJq','https://giftlist-bucket.s3.amazonaws.com/1661869658858user_photo1.jpg', 'USER', 6);

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

insert into bookings(id, user_id)
VALUES (1,3);

insert into holidays(id, holiday_date, name, photo, user_id)
VALUES (1,'2022-03-08','8-март','https://giftlist-bucket.s3.amazonaws.com/1662788168761e13920daf2d40a92b8d0e51d2d374232.jpg',2),
       (2,'2022-12-31','Новый год','https://giftlist-bucket.s3.amazonaws.com/1662788130775istockphoto-1175405816-612x612.jpg',2),
       (3,'2022-03-21','Нооруз','https://giftlist-bucket.s3.amazonaws.com/1662788242139images.jpeg',2),

       (4,'2022-03-08','Happy New Year','https://giftlist-bucket.s3.amazonaws.com/1662788388376Happy-Birthday-580x386.jpg',4),
       (5,'2022-12-31','Birth day','https://giftlist-bucket.s3.amazonaws.com/1661168450017newyear-1173474360-612x612.jpg',4),
       (6,'2022-03-21','Helloween','https://giftlist-bucket.s3.amazonaws.com/1662788550256helloween-3756252_960_720.jpg',4),
       (7,'2022-10-21','Орозо Айт','https://giftlist-bucket.s3.amazonaws.com/1662788930279Orozo Ait.png',6);

insert into wishes(id,created_at,description,wish_link,wish_name,wish_photo,is_block,is_hidden,wish_date,booking_id,from_user_id, holiday,user_id)
values (1,'2021-04-05','Жакет с однобортной застежкой на пуговицу выполнен в двух цветах: черном и синем','https://loverepublic.ru/catalog/odezhda/zhakety/','ЖАКЕТ','https://giftlist-bucket.s3.amazonaws.com/1662789066271Gear-Cloudburst-Jacket---Mandarin-Front-square-grey-back.jpg.webp',false,false,'2021-09-23',1,null,1,2),
       (2,'2022-05-06','Bluetooth 5.0, тип зарядки кейса: Lightning, беспроводная зарядка, степень защиты: IPX4, время работы: 6 ч, время работы от аккумулятора в кейсе: 30 ч, вес: 42.19 г','https://www.kivano.kg/product/view/besprovodnye-naushniki-apple-airpods-3','Apple AirPods 3','https://giftlist-bucket.s3.amazonaws.com/166278917295501-scaled.jpg',false,false,'2022-12-31',null,null,2,2),
       (3,'2022-06-07','Серьги из серебра Opal C3292 шампань циркон 925 проба','https://svetofor.info/sergi-iz-serebra-opal-c3292-shampan-cirkon-925-proba-87.html','Серьги','https://giftlist-bucket.s3.amazonaws.com/1662789328160aef83932-eea8-11eb-b89c-b584e661a910.jpg',false,false,'2023-03-21',null,null,3,2),

       (4,'2022-07-08','Super Retina XDR display OLED, максимальная яркость 1200 нит, iPhone 14 — 6.1", iPhone 14 Plus mdash; 6.1','https://nanoreview.net/ru/phone/apple-iphone-14-pro','Apple iPhone 14 Pro','https://giftlist-bucket.s3.amazonaws.com/1661168510692nooruz.jpg',false,false,'2022-09-01',null,null,4,4),
       (5,'2022-08-09','Игры в динамическом 4K и развлечения в 4K','https://svetofor.info/sony-ps4-igrovaja-pristavka-sony-playstation-4-pro-1tb-belaja.html','PlayStation 4 Pro','https://giftlist-bucket.s3.amazonaws.com/1662789899538sony-ps4-igrovaja-pristavka-sony-playstation-4-pro-1tb-belaja.jpg',false,false,'2022-12-31',null,null,5,4),
       (6,'2022-09-04','Сумка Swisswin SW9730 полиэстер красная','https://svetofor.info/sumka-suissewin-sw9730-krasnyy.html','Сумка','https://giftlist-bucket.s3.amazonaws.com/1662790134925sumka-suissewin-sw9730-krasnyy-1.jpg',false,false,'2023-03-21',null,null,6,4),
       (7,'2022-09-04','Маленький и стильный ручной пылесос Fakir AS1037','https://svetofor.info/pylesos-fakir-as1037.html','Пылесос Fakir AS1037','https://giftlist-bucket.s3.amazonaws.com/1662790265468pylesos-fakir-as1037.jpg',false,false,'2023-03-21',null,null,7,6),
       (8,'2022-09-04','Игрушка Мишка в свитере коричневый','https://svetofor.info/igrushka-mishka-v-svitere-korichnevyj.html','Игрушка Мишка','https://giftlist-bucket.s3.amazonaws.com/1662790534604igrushka-mishka-v-svitere-korichnevyj.jpg',false,false,'2023-03-21',null,null,7,6);

insert into gifts(id, created_at, description, is_block, name, photo, status, booking_id, category_id, from_user_id, sub_category_id, user_id)
VALUES(1,'2022-08-23','Автомобильный держатель Xiaomi JOYROOM',false,'Автомобильный держатель','https://giftlist-bucket.s3.amazonaws.com/1662791456066photo-33_vmsf-py.jpg','USED',1,6,null,32,2),
      (2,'2022-08-23','Кресло-Мешок Добрый Жук Сиеста 100 см',false,'Кресло-Мешок','https://giftlist-bucket.s3.amazonaws.com/1662791616640kreslo-meshok-dobryj-zhuk-siesta-100-sm-1.jpg','USED',null,4,null,19,2),
      (3,'2022-08-23','Кэти Сьерра и Берт Бейтс - Изучаем Java',false,'Книга Изучаем Java','https://giftlist-bucket.s3.amazonaws.com/1662792051602K9aleKbxpiY.jpg','NEW',null,3,null,15,2),

      (4,'2022-08-23','Сумка xiaomi Полиэстер черный',false,'Сумка xiaomi','https://giftlist-bucket.s3.amazonaws.com/1662790738343sumka-xiaomi-arctic-hunter-y00010-polijester-chernyj-1.jpg','USED',null,3,null,14,4),
      (5,'2022-08-23','Туфли Osmanbey кожаные черные',false,'Туфли','https://giftlist-bucket.s3.amazonaws.com/1662790826462tufli-osmanbey-b40979-kozhanye-chernye.jpg','USED',null,5,null,26,4),
      (6,'2022-08-23','Часы настенные Epos Карта Кыргызстана',false,'Часы настенные','https://giftlist-bucket.s3.amazonaws.com/1662791165447chasy-nastennye-epos-karta-kyrgyzstana-700001-1.jpg','NEW',null,2,null,11,4);

insert into friends
VALUES (2,4),
       (4,2),
       (2,5),
       (5,2);

insert into request_to_friends
VALUES (2,6),
       (3,6),
       (4,6),
       (5,6),
       (2,3);

insert into bookings_gifts
VALUES (1,1);

insert into bookings_wishes
VALUES (1,1);