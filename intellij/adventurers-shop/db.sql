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

create table if not exists offers(
	offer_id serial primary key,
	offer_price integer,
	bidder_id integer references users(user_id),
	item_id integer references items(item_id)
);

insert into offers (offer_price, bidder_id, item_id) values ('3', 1, 1);

insert into offers (offer_price, bidder_id, item_id) values ('3', 1, 1);
select offer_price from offers join items on offers.item_id = items.item_id where item_name = 'apple';
select * from offers join items on offers.item_id = items.item_id where item_name = 'apple';

drop table if exists payments;
create table if not exists payments(
    payment_id serial primary key,
    payee_id integer references users(user_id),
    item_id integer references items(item_id),
    amount integer
);

insert into payments (payee_id, item_id, amount) values (1, 1, 2);
insert into payments (payee_id, item_id, amount) values (1, 1, 3);
