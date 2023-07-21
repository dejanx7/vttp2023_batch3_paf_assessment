create database  mybnb;
use mybnb;


create table acc_occupancy (
acc_id varchar(10),
vacancy int,
constraint pk_acc_id primary key (acc_id),
constraint chk_vacancy check (vacancy between 0  and 100)
);

create table reservations(
resv_id varchar(8),
name varchar(128),
email varchar(128),
acc_id varchar(10),
arrival_date date,
duration int,
constraint pk_resv_id primary key (resv_id),
constraint fk_acc_id foreign key (acc_id) references acc_occupancy(acc_id)
);
