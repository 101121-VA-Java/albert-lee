-- This is a single line comment.

--create database revacompany;
/*
	This 
	is
	a
	multiline
	comment
	
	naming convention is snake_case, due to the lack of case sensitivity
*/

-- Creating a schema
--create schema company;
--drop schema company;


drop table if exists users;
create table if not exists users(
	user_id serial primary key,
	user_name varchar(50) UNIQUE not null,
	user_password varchar(50) not null,
	user_role varchar(50),
	cash_on_hand integer not null
);

insert into users (user_name, user_password, user_role, cash_on_hand) values ('1', '1', 'CUSTOMER', 100);
insert into users (user_name, user_password, user_role, cash_on_hand) values ('2', '2', 'EMPLOYEE', 100);

drop table if exists items;
create table if not exists items(
	item_id serial primary key,
	item_name varchar(20),
	item_price integer,
	owner_id integer references users(user_id)
);

insert into items (item_name, item_price, owner_id) values ('apple', '5', null);
insert into items (item_name, item_price, owner_id) values ('orange', '3', 1);
select * from items where owner_id = 1;

create table if not exists offers(
	offer_id serial primary key,
	offer_price integer,
	bidder_id integer references users(user_id),
	item_id integer references items(item_id)
);

insert into offers (offer_price, bidder_id, item_id) values ('3', 1, 1);

--alter table employees 
--	alter column e_name set data type varchar(75);


select e.e_name "Employee name", m.e_name "Manager name"
	from employees e
	join employees m on e.man_e_id = m.e_id 
	where e.e_id = 1;

select t.t_name "task name", t.t_description "task description", t.t_completion_status, e.e_name 
	from tasks t
	join employees e 
	on t.t_assigned_emp =e.e_id 
	where t.id = 1;

-- retrieving all of the tasks for employee of id 1
select * from tasks where t_assigned_emp = 1;

--delete from employees where e_id = 11; -- instead of 11 it might be anything
