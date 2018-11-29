-- AppUser (username, password, appRole)
insert into AppUser (username, password, appRole)
values ("student1", "student1", 0);$
insert into AppUser (username, password, appRole)
values ("student2", "student2", 0);$

insert into AppUser (username, password, appRole)
values ("SJSU_T1", "SJSU_T1", 1);$
insert into AppUser (username, password, appRole)
values ("SJSU_T2", "SJSU_T2", 1);$

insert into AppUser (username, password, appRole)
values ("SCU_T1", "SCU_T1", 1);$
insert into AppUser (username, password, appRole)
values ("SCU_T2", "SCU_T2", 1);$

insert into AppUser (username, password, appRole)
values ("SJSU", "SJSU", 2);$
insert into AppUser (username, password, appRole)
values ("SCU", "SCU", 2);$

insert into AppUser (username, password, appRole)
values ("admin1", "admin1", 3);$

-- WorkFor SJSUinstructor
-- WorkFor (instructorId, instituteId)
-- Need trigger or function to check instructorId exist and instituteId exist
insert into WorkFor (instructorId, instituteId)
values(3, 7);$
insert into WorkFor (instructorId, instituteId)
values(4, 7);$

-- Work for SCU
insert into WorkFor (instructorId, instituteId)
values(5, 8);$
insert into WorkFor (instructorId, instituteId)
values(6, 8);$

-- Course (id, title, instructorId)
-- SJSU DS track include 255, 256, 257
-- Need trigger or function to check instructorId existinstructor
insert into Course (title, instructorId, description)
values ("255", 3, "Data Mining");$
insert into Course (title, instructorId, description)
values ("256", 3, "Large Scale");$
insert into Course (title, instructorId, description)
values ("257", 4, "Machine Learning");$
insert into Course (title, instructorId, description)
values ("258", 4, "Big Data");$
insert into Course (title, instructorId, description)
values ("226", 4, "Database");$
insert into Course (title, instructorId, description)
values ("227", 4, "MySQL");$
insert into Course (title, instructorId, description)
values ("228", 4, "MongoDB");$


-- Homework for 255
insert into Homework(courseId, title, content, type)
values (1, "Data Mining HW1", "asdfasdfsad", 0);$
insert into Homework(courseId, title, content, type)
values (1, "Data Mining HW2", "agdsafds", 0);$
insert into Homework(courseId, title, content, type)
values (1, "Data Mining EXAM", "asdfasdfdsf", 1);$

-- Material for 255
insert into Material(courseId, title, url)
values (1, "255 week1", "asdfasdfasdf");$
insert into Material(courseId, title, url)
values (1, "255 week2", "vdvdvdvd");$
insert into Material(courseId, title, url)
values (1, "255 week3", "adsfasdfsd");$

-- Material for 256
insert into Material(courseId, title, url)
values (2, "256 week3", "adsfasdfsd");$

insert into Discussion(userId, courseId, title, content)
values (3, 1, "instructor post", "aaaaaaa");$
insert into Discussion(userId, courseId, title, content)
values (1, 1, "student post", "babaabab");$

insert into Series (instituteId, title)
values(7, "DS");$

insert into CourseSeries (courseId, seriesId)
values(1, 1);$
insert into CourseSeries (courseId, seriesId)
values(2, 1);$
insert into CourseSeries (courseId, seriesId)
values(3, 1);$

-- -- Enroll (studentId, courseId, isCompleted, isDropped)
insert into Enroll (studentId, courseId)
values(1, 1);$
-- insert into Enroll (studentId, courseId)
-- values(1, 2);$
-- insert into Enroll (studentId, courseId)
-- values(1, 3);$
-- insert into Enroll (studentId, courseId)
-- values(2, 1);$
-- insert into Enroll (studentId, courseId)
-- values(2, 2);$


