drop table if exists ers_reimbursement;
drop table if exists ers_reimbursement_status;
drop table if exists ers_reimbursement_type;
drop table if exists ers_users;
drop table if exists ers_user_roles;
drop table if exists requirements;

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

create table if not exists requirements (
    req_id            serial primary key,
    req_description   varchar(250),
    req_status        integer
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

insert into requirements (req_description) values ('An Employee can login');
insert into requirements (req_description) values ('An Employee can view the Employee Homepage');
insert into requirements (req_description) values ('An Employee can logout');
insert into requirements (req_description) values ('An Employee can submit a reimbursement request');
insert into requirements (req_description) values ('An Employee can upload an image of his/her receipt as part of the reimbursement request (extra credit)');
insert into requirements (req_description) values ('An Employee can view their pending reimbursement requests');
insert into requirements (req_description) values ('An Employee can view their resolved reimbursement requests');
insert into requirements (req_description) values ('An Employee can view their information');
insert into requirements (req_description) values ('An Employee can update their information');
insert into requirements (req_description) values ('An Employee receives an email when one of their reimbursement requests is resolved (optional)');
insert into requirements (req_description) values ('A Manager can login');
insert into requirements (req_description) values ('A Manager can view the Manager Homepage');
insert into requirements (req_description) values ('A Manager can logout');
insert into requirements (req_description) values ('A Manager can approve/deny pending reimbursement requests');
insert into requirements (req_description) values ('A Manager can view all pending requests from all employees');
insert into requirements (req_description) values ('A Manager can view images of the receipts from reimbursement requests (extra credit)');
insert into requirements (req_description) values ('A Manager can view all resolved requests from all employees and see which manager resolved it');
insert into requirements (req_description) values ('A Manager can view all Employees');
insert into requirements (req_description) values ('A Manager can view reimbursement requests from a single Employee');

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('1', '1', '1', '1', '1@1', 1, null);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('2', '$2a$12$BDowLWZhZYoheNSxxaOwQuUCVHKZZ6cphGgjFgt2Fgc8ewvLSPMJq', 'manager2', '2', '2@2', 2, 1);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('3', '$2a$12$zScPAbgeor0L8PgmLX.KvuK8iZDK/BRIuBRPEVWrN4iAFTug9dFjC', 'emp3', '3', '3@3', 3, 1);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('4', '$2a$12$TmINrpON.yyGE9W4YOe43OOiQSbKtuv4QKtGGT05c4QQDErNF3uf.', 'manager4', '4', '4@4', 2, 1);

insert into ers_users 
(ers_username, ers_password, user_first_name, 
user_last_name, user_email, user_role_id, ers_manager_id)
values
('5', '$2a$12$FgvzbkeHMd9PmzhcSvhUxecuOJ1A5MCtxkOkPCMlCyEtuwIFK8rBS', 'emp5', '5', '5@5', 3, 1);


insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'lol', 3, 3);

insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'yes', 3, 3);

insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'no', 4, 3);

insert into ers_reimbursement 
(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
values
(100, current_timestamp, 'bananas', 4, 3);

insert into ers_reimbursement 
(reimb_status_id, reimb_resolver, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_type_id)
values
(2, 2, 700000, current_timestamp, current_timestamp, 'exotic truffles', 4, 3);

insert into ers_reimbursement 
(reimb_status_id, reimb_resolver, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_type_id)
values
(1, 2, 1000, current_timestamp, current_timestamp, 'office paper', 4, 4);