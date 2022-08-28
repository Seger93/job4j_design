create table auto(
    id serial primary key,
    name varchar(255)
);

create table family(
    id serial primary key,
    name varchar(255),
    auto_id int references auto(id)
);

insert into auto(name) values ('opel');

insert into family(name, auto_id) VALUES ('husband', 1);

select * from family;

select * from auto where id in (select id from family);

 create table people(
     id serial primary key,
     name varchar(255)
 );

 create table works(
     id serial primary key,
     name varchar(255)
 );

 create table people_works(
     id serial primary key,
     people_id int references people(id),
     works_id int references works(id)
 );

insert into people(name) values ('Ivan');
insert into people(name) values ('Kirill');
insert into people(name) values ('Roman');

insert into works(name) values ('Ingeneer');
insert into works(name) values ('driver');
insert into works(name) values ('cleaner');

insert into people_works(people_id, works_id) values (1, 1);
insert into people_works(people_id, works_id) values (2, 1);
insert into people_works(people_id, works_id) values (3, 2);

create table phone_number(
    id serial primary key,
    seria int,
    number int
);

create table people_phone(
    id serial primary key,
    name varchar(255),
    phone_number_id int references phone_number(id) unique
);

insert into phone_number(seria) values (88888888);
insert into people_phone(phone_number_id, name) values (2, 'Sergei');