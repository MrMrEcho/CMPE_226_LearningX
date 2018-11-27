DROP TABLE IF EXISTS AppUser;
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



