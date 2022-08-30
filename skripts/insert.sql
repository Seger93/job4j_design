insert into users(name) VALUES ('Sergei');

insert into role(name, users_id) values ('brewer', 1);

insert into rules(name) values ('craft');

insert into rules_role(rules_id, role_id) values (1, 1);

insert into category(name) VALUES ('OFF');

insert into state(name) VALUES ('works');

insert into item(name, users_id, category_id, state_id) VALUES ('№', 1, 1, 1);

insert into comments(name, item_id) values ('not ok', 1);

insert into attachs(name, item_id) values ('pdf_file', 1);