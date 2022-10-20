create table auto (
     id serial primary key,
     model text,
     mileage double precision,
     wheels varchar(200),
     age integer
);

insert into auto(model, mileage, wheels, age)
values('opel', 155.23, 16, 2011);
insert into auto(model, mileage, wheels, age)
values('bmw', 555.66, 20, 2021);
insert into auto(model, mileage, wheels, age)
values('kia', 145.23, 19, 2010);

start transaction;

update auto set model = 'volvo' where age = 2010;

select * from auto;

commit transaction;

start transaction ;

insert into auto(model, mileage, wheels, age)
values('mazda', 199.23, 19, 2015);

savepoint first_savepoint;

select * from auto;

delete from auto where age < 2015;

select * from auto;

rollback to first_savepoint;

commit transaction;

