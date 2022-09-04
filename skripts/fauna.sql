create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('predator', 500000, date '1900-02-01');
insert into fauna(name, avg_age, discovery_date) values ('fish', 100500, date '1800-03-01');
insert into fauna(name, avg_age, discovery_date) values ('fish1', 10050, date '1800-03-01');
insert into fauna(name, avg_age, discovery_date) values ('insects', 5000, date '1500-04-01');
insert into fauna(name, avg_age, discovery_date) values ('birds', 50000, date '1200-05-01');
insert into fauna(name, avg_age, discovery_date) values ('amphibians', 21000, date '1000-05-01');
insert into fauna(name, avg_age) values ('crayfish', 21000);

select * from fauna where name = 'fish';
select * from fauna where name like 'fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';