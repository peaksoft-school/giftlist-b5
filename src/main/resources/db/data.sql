insert into users_info(id, city, clothing_size, date_of_birth, facebook_link, hobby, important_note, instagram_link,phone_number, shoe_size, telegram_link, vk_link)
values (1, 'California', 'XXL', '2004-12-12', 'facebook.com', 'playing pubg', 'I love eat', 'instagram.com','0777 345 342', 34, 'telegram.com', 'vk.com'),
       (2, 'Texas', 'XS', '1999-05-15', 'facebook.com', 'music', 'I love soccer', 'instagram.com', '0777 345 879', 42, 'telegram.com', 'vk.com');

insert into users(id, email, first_name, is_block, is_friend, last_name, password, photo, role, user_info_id)
VALUES (1, 'admin@gmail.com', 'Admin', true, false, 'string','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba', 'photo', 'ADMIN', 1),
       (2, 'user@gmail.com', 'USER', true, false, 'string','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW','photo', 'USER', 2);

insert into categories(id, name)
values (1, 'Электроника'),(2,'Одежда'),(3,'Школа'),(4,'Дом и сад'),(5,'Обувь'),(6,'Транспорт');