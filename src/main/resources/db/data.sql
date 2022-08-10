insert into users(id, email, first_name, friend_status, is_block, last_name, password, role)
VALUES (1,'admin@gmail.com','Admin','NOT_FRIEND',true,'string','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba','ADMIN'),
 (2,'user@gmail.com','USER','NOT_FRIEND',true,'string','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW','USER');

insert into users(id, email, first_name, friend_status, is_block, last_name, password, role, user_info_id)
VALUES (1, 'admin@gmail.com', 'Admin', 'NOT_FRIEND', true, 'string','$2a$12$xEFzerKnyLVgXyBQ/ecOjuVs5rDd2KgixXHHvPSIqTN7TDnRH0Oba', 'ADMIN', 1),
       (2, 'user@gmail.com', 'USER', 'NOT_FRIEND', true, 'string','$2a$12$YVsqnm/x/Z4xxsqzdPIAWeq3TNLZx/KEmMvAMQ4i8obMHd9YkxhYW', 'USER', 2);

insert into categories(id, name)
values (1, 'Электроника'),(2,'Одежда'),(3,'Школа'),(4,'Дом и сад'),(5,'Обувь'),(6,'Транспорт');