create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);
insert into departments(name) values ('Налоговая');
insert into departments(name) values ('Налоговая');
insert into departments(name) values ('ФСО');
insert into departments(name) values ('Минздрав');
insert into departments(name) values ('ФСБ');

insert into employees(name, departments_id) values ('Миша', 1);
insert into employees(name, departments_id) values ('Коля', 2);
insert into employees(name, departments_id) values ('Вася', 3);
insert into employees(name, departments_id) values ('Вася', 4);
insert into employees(name, departments_id) values ('Оля', 4);
insert into employees(name, departments_id) values ('Сергей', 1);
insert into employees(name, departments_id) values ('Петя', null);
insert into employees(name, departments_id) values ('Лена', null );

--many-to-one, выполнять присоединение таблицы, которая one, к таблице, которая many.
select * from departments d
left join employees e on d.id = e.departments_id;

select * from departments d
right join employees e on d.id = e.departments_id;

select * from departments d
full join employees e  on d.id = e.departments_id;

select * from departments d
cross join employees e;

--Используя left join найти департаменты, у которых нет работников.
select * from departments d
left join employees e on d.id = e.departments_id
where departments_id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат
select * from departments d
left join employees e on d.id = e.departments_id
where departments_id is not null;

select * from departments d
right join employees e on d.id = e.departments_id
where departments_id is not null;

--все возможные разнополые пары.
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Миша', 'Мужчина');
insert into teens(name, gender) values ('Оля', 'Женщина');
insert into teens(name, gender) values ('Петя', 'Мужчина');

select t1.name as n, t2.name as g
from teens t1 cross join teens t2
where (t1.gender != t2.gender);
