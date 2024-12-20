drop table if exists ticket;
drop table if exists user;

create table user(
	user_id int not null AUTO_INCREMENT,
	name varchar(200) not null,
	primary key(user_id)
);

create table ticket(
	ticket_id int not null AUTO_INCREMENT,
	user_id integer,
	assignment_date datetime,
	version long not null,
	creation_date datetime not null,
	update_date datetime not null,
	primary key(ticket_id),
	foreign key(user_id) references user(user_id)
);

insert into user(user_id, name) values (1, 'Adrian'), (2, 'Elena'), (3, 'Ezequiel');

insert into ticket(ticket_id, version, creation_date, update_date) 
values (1, 0, now(), now()), (2, 0, now(), now()), (3, 0, now(), now()), (4, 0, now(), now()), (5, 0, now(), now());

select * from user;
select * from ticket;

