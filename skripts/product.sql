create table type (
    id serial primary key,
    name text
);

create table product (
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженное');
insert into type(name) values ('Хлеб');
insert into type(name) values ('Соус');

insert into product(name, type_id, expired_date, price ) values ('Голладский', 1, date '2022-09-22', 450);
insert into product(name, type_id, expired_date, price ) values ('Гауда', 1, date '2022-09-21', 455);
insert into product(name, type_id, expired_date, price ) values ('Cыр плавленный', 1, date '2022-10-01', 300);
insert into product(name, type_id, expired_date, price ) values ('Cыр дружба', 1, date '2022-10-01', 700);
insert into product(name, type_id, expired_date, price ) values ('Сыр Чеддер', 1, date '2022-09-07', 700);
insert into product(name, type_id, expired_date, price ) values ('Отборное', 2, date '2022-09-16', 550);
insert into product(name, type_id, expired_date, price ) values ('Обычное', 2, date '2022-09-19', 400);
insert into product(name, type_id, expired_date, price ) values ('Топлёное', 2, date '2022-09-17', 399);
insert into product(name, type_id, expired_date, price ) values ('Пломбир', 3, date '2022-09-20', 165);
insert into product(name, type_id, expired_date, price ) values ('Мороженное Эскимо', 3, date '2022-09-19', 150);
insert into product(name, type_id, expired_date, price ) values ('Мороженное Стаканчик', 3, date '2022-09-07', 100);
insert into product(name, type_id, expired_date, price ) values ('Столичный', 4, date '2022-09-17', 70);
insert into product(name, type_id, expired_date, price ) values ('Белый', 4, date '2022-09-07', 55);
insert into product(name, type_id, expired_date, price ) values ('Дарницкий', 4, date '2022-09-15', 60);
insert into product(name, type_id, expired_date, price ) values ('Кутчуп', 5, date '2022-09-25', 60);
insert into product(name, type_id, expired_date, price ) values ('Майонез', 5, date '2022-09-22', 60);
insert into product(name, type_id, expired_date, price ) values ('Горчица', 5, date '2022-09-07', 50);
insert into product(name, type_id, expired_date, price ) values ('Кетчунез', 5, date '2022-09-16', 50);
insert into product(name, type_id, expired_date, price ) values ('Сырный', 5, date '2022-09-01', 50);

 -- Все продукты с типом Сыр
select * from product as p
join type as t
on p.type_id= t.id
where t.name like '%Сыр%';

select * from  product where name LIKE '%Мороженное%'; -- Все продукты которые содержат искомое слово.

select * from  product where expired_date < current_date; -- Все продукты с истекшим сроком годности.

-- максимальная стоимость продукта.
select p.name, p.price, p.expired_date, t.name
from product as p
join type as t
on p.type_id= t.id
group by p.name, p.price, p.expired_date, t.name
having p.price = (select max(price) from product);

select t.name, count(p.type_id) -- выбираем сортировку по названию продукта и считаем общее количество из таблицы продуктов.
from product as p -- выборка из таблицы продуктов и присвоим ей символ.
join type t on p.type_id = t.id -- присоединим к таблице продуктов, таблицу типов при условии совпадения столбцов.
group by t.name -- сгруппируем по названию типа продукта.

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product as p
join type as t
on p.type_id= t.id
where t.name like '%Сыр%' OR t.name like '%Молоко%';

--Написать запрос, который выводит тип продуктов, которых осталось меньше 4 штук.
select t.name, count(p.type_id)
from product as p
join type t on p.type_id = t.id
group by t.name
having count(p.type_id) < 4;

-- Вывести все продукты и их тип.
select p.name, t.name
from product as p
join type t on p.type_id = t.id
group by p.name, t.name order by t.name asc;
















