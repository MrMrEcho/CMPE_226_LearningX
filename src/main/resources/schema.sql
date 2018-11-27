drop table if exists Discussion;
drop table if exists Submission;
drop table if exists Homework;
drop table if exists Material;
drop table if exists Rating;
drop table if exists Enroll;
drop table if exists CourseSeries;
drop table if exists Course;
drop table if exists Series;
drop table if exists WorkFor;
drop table if exists AppUser;

create table AppUser
(
    id          int auto_increment,
    username    varchar(32) NOT NULL,
    password    varchar(32) NOT NULL,
    appRole     int,

    primary key (id)
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
    id              int auto_increment,
    title           varchar(128) not null,
    instructorId    int,

    primary key (id),

    foreign key (instructorId) references AppUser(id)
    on delete cascade
);

create table WorkFor
(
    instructorId    int,
    instituteId     int,

    primary key (instructorId, instituteId),

    foreign key (instructorId) references AppUser(id)
    on delete cascade,
    foreign key (instituteId) references AppUser(id)
    on delete cascade
);

create table Series
(
    id          int auto_increment,
    instituteId int,
    title       varchar(128) not null,

    primary key (id),

    foreign key (instituteId) references AppUser(id)
    on delete cascade
);

create table CourseSeries
(
    courseId    int,
    seriesId    int,

    primary key (courseId, seriesId),

    foreign key (courseId) references Course(id)
    on delete cascade,

    foreign key (seriesId) references Series(id)
    on delete cascade
);

create table Enroll
(
    studentId   int,
    courseId    int,
    isCompleted boolean default false,
    isDropped boolean default false,

    primary key (studentId, courseId),

    foreign key (studentId) references AppUser(id)
    on delete cascade,

    foreign key (courseId) references Course(id)
    on delete cascade
);

create table Rating
(
    studentId   int,
    courseId    int,
    rating      int,

    primary key (studentId, courseId),

    foreign key (studentId) references AppUser(id)
    on delete cascade,

    foreign key (courseId) references Course(id)
    on delete cascade
);


create or replace view AverageRating
as
select C.id as id, C.title as title, avg(R.rating) as rating, count(distinct R.studentId) as count
from Course C natural join Rating R
group by C.id;

create table Material
(
    id          int auto_increment,
    courseId    int,
    title       varchar(256) not null,
    url         varchar(256),

    primary key (id),

    foreign key (courseId) references Course(id)
    on delete cascade
);

create table Homework
(
    id          int auto_increment,
    courseId    int,
    title       varchar(256) not null,
    content     varchar(256),
    type        int not null,

    primary key (id),

    foreign key (courseId) references Course(id)
    on delete cascade
);

create table Submission
(
    userId      int,
    homeworkId  int,
    answer      varchar(256) not null,
    grade       int,
    isGraded    boolean default false,

    primary key (userId, homeworkId),

    foreign key (userId) references AppUser(id)
    on delete cascade,

    foreign key (homeworkId) references Homework(id)
    on delete cascade
);

--delimiter $$
--create trigger AfterSubmissionGraded
--    after update on Submission
--    for each row
--begin
--    if NEW.isGraded and isExam(NEW.homeworkId) and NEW.grade >= 60 then
--        setCompleted(NEW.userId, getCourseIdByHomeworkId(NEW.homeworkId));
--    end if;
--end $$
--delimiter;

create table Discussion
(
    userId      int,
    courseId    int,
    title       varchar(128) not null,
    content     varchar(256),

    primary key (userId, courseId),

    foreign key (userId) references AppUser(id)
    on delete cascade,

    foreign key (courseId) references Course(id)
    on delete cascade
);