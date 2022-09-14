create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 500);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 3, 100);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 3, 150);

create or replace procedure delete_data(d_id integer)
    language 'plpgsql'
as
$$
BEGIN
    delete from products where id = d_id;
END
$$;

call delete_data(1);

create or replace function f_delete_data(d_id integer)
    returns void
    language 'plpgsql'
as
$$
begin
    delete from products where id = d_id;
end;
$$;

select f_delete_data(3);
