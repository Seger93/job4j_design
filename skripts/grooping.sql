create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 1999.99);
insert into devices(name, price) values ('TV', 3500);
insert into devices(name, price) values ('console', 4990);
insert into devices(name, price) values ('PC', 12000);
insert into devices(name, price) values ('player', 55.99);

insert into people(name) values ('Vasya');
insert into people(name) values ('Petya');
insert into people(name) values ('Sergei');
insert into people(name) values ('Oleg');
insert into people(name) values ('Nastya');

insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (2, 5);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (1, 4);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (5, 1);

select avg(price) from devices; -- средняя цена устройств.

select p.name, avg(d.price) -- выбираем сортировку по имени из таблицы людей и среднюю стоимость устройства.
from people as p -- из таблицы людей делаем выборку и присвоим ей символ.
join devices_people dp on dp.people_id = p.id -- присоединим к таблице людей поле таблицы девайсов людей при условии совпадения полей.
join devices d on dp.device_id = d.id -- -//-
group by p.name -- сгруппируем по имени.

having avg(d.price) > 5000; -- средняя цена больше 5000.

