-- WARNING:  create users in console after creating tables but before seeding data, because BCrypt generates the stored password

drop table if exists ers_reimbursement;
drop table if exists ers_reimbursement_status;
drop table if exists ers_reimbursement_type;
drop table if exists ers_users;
drop table if exists ers_user_roles;

create table if not exists ers_user_roles (
    ers_user_role_id   serial primary key,
    user_role          varchar(10)
);

create table if not exists ers_users (
    ers_users_id      serial primary key,
    ers_username      varchar(50) UNIQUE not null,
    ers_password      varchar(200) not null,
    user_first_name   varchar(100),
    user_last_name    varchar(100),
    user_email        varchar(150),
    user_role_id      integer references ers_user_roles(ers_user_role_id),
    ers_manager_id    integer references ers_users(ers_users_id)
);

create table if not exists ers_reimbursement_type (
    reimb_type_id     serial primary key,
    reimb_type        varchar(10)
);

create table if not exists ers_reimbursement_status (
    reimb_status_id   serial primary key,
    reimb_status      varchar(10) unique
);

create table if not exists ers_reimbursement (
    reimb_id          serial primary key,
    reimb_amount      integer not null,
    reimb_submitted   timestamp not null,
    reimb_resolved    timestamp,
    reimb_description varchar(250),
    reimb_receipt     bytea,
    reimb_author      integer references ers_users(ers_users_id),
    reimb_resolver    integer references ers_users(ers_users_id),
    reimb_status_id   integer references ers_reimbursement_status(reimb_status_id),
    reimb_type_id     integer references ers_reimbursement_type(reimb_type_id)
);

insert into ers_user_roles (user_role) values ('ADMIN');
insert into ers_user_roles (user_role) values ('MANAGER');
insert into ers_user_roles (user_role) values ('BASIC');

insert into ers_reimbursement_status (reimb_status) values ('APPROVED');
insert into ers_reimbursement_status (reimb_status) values ('DENIED');

insert into ers_reimbursement_type (reimb_type) values ('LODGING');
insert into ers_reimbursement_type (reimb_type) values ('TRAVEL');
insert into ers_reimbursement_type (reimb_type) values ('FOOD');
insert into ers_reimbursement_type (reimb_type) values ('OTHER');

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('1', '1', '1', '1', '1@1', 1, null);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('2', '2', '2', '2', '2@2', 2, 1);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('3', '3', '3', '3', '3@3', 3, 1);

insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'lol', 3, 3);
insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'lol', 3, 3);