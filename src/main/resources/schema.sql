-- delimiter ;$

drop table if exists Discussion;$
drop table if exists Submission;$
drop table if exists Homework;$
drop table if exists Material;$
drop table if exists Rating;$
drop table if exists Enroll;$
drop table if exists CourseSeries;$
drop table if exists Course;$
drop table if exists Series;$
drop table if exists WorkFor;$
drop table if exists AppUser;$

create table AppUser
(
    id          int auto_increment,
    username    varchar(32) NOT NULL,
    password    varchar(512) NOT NULL,
    appRole     int,
    
    primary key (id)
);$

create table Course
(
    id              int auto_increment,
    title           varchar(128) not null,
    description     varchar(256),
    instructorId    int,
    
    primary key (id),
    
    foreign key (instructorId) references AppUser(id)
        on delete cascade,

    fulltext index course_content (title, description)
);$

create table WorkFor
(
    instructorId    int,
    instituteId     int,
    
    primary key (instructorId, instituteId),
    
    foreign key (instructorId) references AppUser(id)
        on delete cascade,
    foreign key (instituteId) references AppUser(id)
        on delete cascade
);$

create table Series
(
    id          int auto_increment,
    instituteId int,
    title       varchar(128) not null,
    
    primary key (id),
    
    foreign key (instituteId) references AppUser(id)
        on delete cascade
);$

create table CourseSeries
(
    courseId    int,
    seriesId    int,
    
    primary key (courseId, seriesId),
    
    foreign key (courseId) references Course(id)
        on delete cascade,
    
    foreign key (seriesId) references Series(id)
        on delete cascade
);$

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
);$

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
);$

create table Material
(
    id          int auto_increment,
    courseId    int not null,
    title       varchar(256) not null,
    url         varchar(256),
    
    primary key (id),
    
    foreign key (courseId) references Course(id)
        on delete cascade
);$

create table Homework
(
    id          int auto_increment,
    courseId    int not null,
    title       varchar(256) not null,
    content     varchar(256),
    type        int not null,
    
    primary key (id),
    
    foreign key (courseId) references Course(id)
        on delete cascade
);$

create table Submission
(
    studentId   int,
    homeworkId  int,
    answer      varchar(256) not null,
    grade       int,
    isGraded    boolean default false,
    
    primary key (studentId, homeworkId),
    
    foreign key (studentId) references AppUser(id)
        on delete cascade,
    
    foreign key (homeworkId) references Homework(id)
        on delete cascade
);$

create table Discussion
(
    id          int auto_increment,
    -- userId      int,
    -- courseId    int,
    userId      int not null,
    courseId    int not null,
    title       varchar(128) not null,
    content     varchar(256),
    
    -- primary key (userId, courseId),
    primary key (id),
    
    -- FIXME: remove user discussion still can keep
    foreign key (userId) references AppUser(id)
        on delete cascade,
    
    foreign key (courseId) references Course(id)
        on delete cascade
);$

-- Create Views
create or replace view Student
as
select * from AppUser where appRole=0;$

create or replace view Instructor
as
select * from AppUser where appRole=1;$

create or replace view Institute
as
select * from AppUser where appRole=2;$

create or replace view AdminUser
as
select * from AppUser where appRole=3;$

create or replace view AverageRating
as
select C.id as id, C.title as title, avg(R.rating) as rating, count(distinct R.studentId) as count
from Course C join Rating R on C.id = R.courseId
group by C.id;$

-- Create stored procedures
drop function if exists isExam ;$
create function `isExam` (homeworkId int)
    returns boolean
    READS SQL DATA
    DETERMINISTIC
begin
    declare htype int;
    select type into htype from Homework where id = homeworkId;
    if htype = 1 then
        return true;
    else
        return false;
    end if;
end ;$


drop function if exists getCourseIdByHomeworkId ;$
create function `getCourseIdByHomeworkId` (homeworkId int)
    returns int
    READS SQL DATA
    DETERMINISTIC
begin
    declare cid int;
    select courseId into cid from Homework where id = homeworkId;
    return cid;
end ;$


drop procedure if exists completeEnroll ;$
create procedure `completeEnroll` (studentId int, cid int)
begin
    update Enroll E
    set isCompleted = true
    where E.studentId = studentId and E.courseId = cid;
end ;$

-- Create triggers

drop trigger if exists AfterSubmissionGraded ;$
create trigger `AfterSubmissionGraded`
    after update on Submission
    for each row
begin
    declare cid int;
    if NEW.isGraded and NEW.grade >= 60 and isExam(NEW.homeworkId) then
        set cid = getCourseIdByHomeworkId(NEW.homeworkId);
        call completeEnroll(NEW.studentId, cid);
    end if;
end ;$

-- delimiter ;

