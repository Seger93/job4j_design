create table cars (
	id serial primary key,
	model text,
	mileage double precision,
	wheels varchar(200),
	age integer
);

insert into cars(model, mileage, wheels, age) values('opel', 155.23, 16, 2011);

update cars set model = 'volvo';

delete from cars;