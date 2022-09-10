create table employees(
    id serial primary key,
    name varchar(255)
);

create table departments(
    id serial primary key,
    name varchar(255),
    employees_id int references employees(id)
);

insert into employees(name) values ('Миша');
insert into employees(name) values ('Коля');
insert into employees(name) values ('Вася');

insert into departments(name, employees_id) values ('Налоговая',1);
insert into departments(name, employees_id) values ('Налоговая',2);
insert into departments(name, employees_id) values ('ФСО', 2);
insert into departments(name, employees_id) values ('Водоканал', 3);
insert into departments(name, employees_id) values ('Минздрав', null);
insert into departments(name, employees_id) values ('ФСБ', null);

--many-to-one, выполнять присоединение таблицы, которая one, к таблице, которая many.
select * from departments d left join employees e on e.id = d.employees_id;

select * from departments d right join employees e on e.id = d.employees_id;

select * from departments d full join employees e on e.id = d.employees_id;

select * from departments d cross join employees e;

--Используя left join найти департаменты, у которых нет работников.
select * from departments d left join employees e on e.id = d.employees_id where employees_id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат
select * from departments d left join employees e on e.id = d.employees_id where employees_id is not null;

select * from departments d right join employees e on e.id = d.employees_id;

--все возможные разнополые пары.
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Миша', 'Мужчина');
insert into teens(name, gender) values ('Оля', 'Женщина');
insert into teens(name, gender) values ('Петя', 'Мужчина');

select t1.name as n, t2.name as g from teens t1 cross join teens t2 where (t1.gender != t2.gender);
