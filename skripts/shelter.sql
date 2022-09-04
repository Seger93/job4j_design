create table shelter (
    id serial primary key,
    name text
);

create table pets (
    id serial primary key,
    age int,
    nickname text,
    shelter_id int references shelter(id)
);

insert into shelter(name) values ('cats');
insert into shelter(name) values ('dog');
insert into shelter(name) values ('chupokabra');
insert into pets(shelter_id, age, nickname) values (1, 10, 'razer');
insert into pets(shelter_id, age, nickname) values (1, 5, 'skull');
insert into pets(shelter_id, age, nickname) values (1, 15, 'raptor');
insert into pets(shelter_id, age, nickname) values (2, 2, 'doom');
insert into pets(shelter_id, age, nickname) values (2, 16, 'rich');
insert into pets(shelter_id, age, nickname) values (3, 1000, 'WTF');

select pp.nickname
from pets as pp join shelter as p on pp.shelter_id = p.id;

select p.name, pp.age
from pets as pp join shelter as p on pp.shelter_id = p.id;

select p.name, pp.age, pp.nickname
from pets as pp join shelter as p on pp.shelter_id = p.id;