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
    studentId      int,
    homeworkId  int,
    answer      varchar(256) not null,
    grade       int,
    isGraded    boolean default false,

    primary key (studentId, homeworkId),

    foreign key (studentId) references AppUser(id)
    on delete cascade,

    foreign key (homeworkId) references Homework(id)
    on delete cascade
);

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

-- Create Views
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

create or replace view AverageRating
as
select C.id as id, C.title as title, avg(R.rating) as rating, count(distinct R.studentId) as count
from Course C natural join Rating R
group by C.id;

-- Create stored procedures
delimiter $$;
create function `isExam` (homeworkId int)
return boolean
begin
	set @homeworkType = (
		select type from Homework H where H.homeworkId = homeworkId
    );
    if homeworkType = 1 then
		return true;
    else
		return false;
    end if;
end $$ delimiter ;

delimiter $$;
create function `getCourseIdByHomeworkId` (homeworkId int)
return int
begin
	set @courseId = (
		select courseId from Homework where id = homeworkId
    );
    return courseId;
end $$ delimiter ;

delimiter $$
create procedure `completeEnroll` (studentId int, courseId int)
begin
	update Enroll E join Course
    set isComplete = true
    where E.studentId = studentId and E.courseId = courseId;
end $$ delimiter ;


-- Create triggers
delimiter $$
create trigger `AfterSubmissionGraded`
    after update on Submission
    for each row
begin
    if NEW.isGraded and NEW.grade >= 60 and isExam(NEW.homeworkId) then
        call completeEnroll(NEW.studentId, getCourseIdByHomeworkId(NEW.homeworkId));
    end if;
end $$ delimiter ;

