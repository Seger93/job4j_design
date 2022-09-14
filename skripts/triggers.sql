create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price + 20
    where id = (select id from inserted);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_trigger --мы создаем триггер и присваиваем ему имя tax_trigger
    after insert --что триггер будет срабатывать до события вставки данных в таблицу products.
    on products
    referencing new table as inserted
    for each statement --регламентирует, что триггер будет выполнен для каждой таблицы
-- При этом для выполнения триггера вызывается функция tax()
execute procedure tax();

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

--Триггер на на вставку в строку.
create or replace function tax1()
    returns trigger as
$$
BEGIN
    update products
    set price = price + 20
    where id = (select id from products);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax1_trigger
    before insert
    on products
    for each row
execute procedure tax1();

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

--триггер заполняющий таблицу история прайса.
create or replace function his()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date)
    VALUES (NEW.name, new.price, date(current_date));
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger update_price_trigger
    after insert
    on products
    for each row
execute procedure his();

insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_1', 3, 900);