-- WARNING:  create users in console after creating tables but before seeding data, because BCrypt generates the stored password

drop table if exists ers_reimbursement;
drop table if exists ers_reimbursement_status;
drop table if exists ers_reimburseent_type;
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

create table if not exists ers_reimburseent_type (
    reimb_type_id     serial primary key,
    reimb_type        varchar(10)
);

create table if not exists ers_reimbursement_status (
    reimb_status_id   serial primary key,
    reimb_status      varchar(10)
);

create table if not exists ers_reimbursement (
    reimb_id          serial primary key,
    reimb_amount      integer not null,
    reimb_submitted   timestamp not null,
    reimb_resolved    timestamp not null,
    reimb_description varchar(250),
    reimb_receipt     json,
    reimb_author      integer references ers_users(ers_users_id),
    reimb_resolver    integer references ers_users(ers_users_id),
    reimb_status_id   integer references ers_reimbursement_status(reimb_status_id),
    reimb_type_id     integer references ers_reimburseent_type(reimb_type_id)
);