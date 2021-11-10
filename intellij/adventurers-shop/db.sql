drop table if exists payments;
drop table if exists offers;
drop table if exists items;
drop table if exists users;

create table if not exists users
(
    user_id       serial primary key,
    user_name     varchar(50) UNIQUE not null,
    user_password varchar(200)       not null,
    user_role     varchar(50)        not null
);

create table if not exists items
(
    item_id    serial primary key,
    item_name  varchar(100) not null,
    item_price integer      not null,
    item_type  varchar(10),
    owner_id   integer references users (user_id)
);

create table if not exists offers
(
    offer_id  serial primary key,
    bid_price integer not null,
    bidder_id integer not null references users (user_id),
    item_id   integer not null references items (item_id)
);

create table if not exists payments
(
    payment_id serial primary key,
    payee_id   integer not null references users (user_id),
    item_id    integer not null references items (item_id),
    amount     integer not null
);

--create username 1 in console because BCrypt generates the stored password
--create username 2 in console because BCrypt generates the stored password

insert into items (item_name, item_price, item_type, owner_id)
values ('apple', '5', 'BORING', null);
insert into items (item_name, item_price, item_type, owner_id)
values ('orange', '3', 'BORING', 1);
insert into items (item_name, item_price, item_type, owner_id)
values ('potion that might cure all diseases or kill you', '42', 'RISKY', null);

insert into offers (bid_price, bidder_id, item_id)
values (3, 1, 1);

insert into payments (payee_id, item_id, amount)
values (1, 1, 2);
insert into payments (payee_id, item_id, amount)
values (1, 1, 3);