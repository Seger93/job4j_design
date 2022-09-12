create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    car_transmissions_id int references car_transmissions(id),
    car_engines_id int references car_engines(id),
    car_bodies_id int references car_bodies(id)
);

insert into car_bodies(name) values ('Седан'),('Хэтчбек'),('Универсал'),('Пикап'),('Купе');
insert into car_engines(name) values ('V8'),('V6'),('V4'),('Электро'),('Дизель');
insert into car_transmissions(name) values ('Автоматическая'),('Роботизированная'),('Механика'),('DSg');
insert into cars(name, car_transmissions_id, car_engines_id , car_bodies_id)
values ('Вольво', 1, 1, 2), ('Опель', null, 3, 4), ('БМВ', 3, 1, 4),('Ауди', 2, 3, null),('Киа',1, null, 3);

--Создаем представление.
create view all_cars_and_all_details
    as select c.id, c.name car_name, cb.name body_name,
    ce.name engine_name, ct.name transmission_name
    from cars c
    left join car_bodies cb on c.car_bodies_id = cb.id
    left join car_engines ce on c.car_engines_id = ce.id
    left join car_transmissions ct on c.car_transmissions_id = ct.id

select * from all_cars_and_all_details;

--переименовать представление.
alter view all_cars_and_all_details rename to show_all_cars_and_all_details;

--удалить представление.
drop view show_all_cars_and_all_details;