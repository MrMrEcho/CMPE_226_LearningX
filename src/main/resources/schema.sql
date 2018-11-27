drop table if exists Course;
drop table if exists AppUser;

CREATE TABLE AppUser
(
    id          int auto_increment  primary key,
    username    varchar(32) NOT NULL,
    password    varchar(32) NOT NULL,
    appRole     int
);

create or replace view Student
as
select * from AppUser where appRole=0;

create or replace view Instructor
as
select * from AppUser where appRole=1;

create or replace view Institute
as
select * from AppUser where appRole=2;

create or replace view AdminUser
as
select * from AppUser where appRole=3;

create table Course
(
    id              int auto_increment  primary key,
    title           varchar(128) NOT NULL,
    instructorId    int ,

    foreign key (instructorId) references AppUser(id)
    on delete cascade
);


