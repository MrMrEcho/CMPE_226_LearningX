-- WorkFor SJSUinstructor
-- WorkFor (instructorId, instituteId)
-- Need trigger or function to check instructorId exist and instituteId exist
insert into WorkFor (instructorId, instituteId)
values(2, 3);$

-- Course (id, title, instructorId)
-- SJSU DS track include 255, 256, 257
-- Need trigger or function to check instructorId existinstructor
insert into Course (title, instructorId, description)
values ("255", 2, "Data Mining");$
insert into Course (title, instructorId, description)
values ("256", 2, "Large Scale");$
insert into Course (title, instructorId, description)
values ("257", 2, "Machine Learning");$
insert into Course (title, instructorId, description)
values ("258", 2, "Big Data");$
insert into Course (title, instructorId, description)
values ("226", 2, "Database");$
insert into Course (title, instructorId, description)
values ("227", 2, "MySQL");$
insert into Course (title, instructorId, description)
values ("228", 2, "MongoDB");$


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
values (2, 1, "instructor post", "aaaaaaa");$
insert into Discussion(userId, courseId, title, content)
values (1, 1, "student post", "babaabab");$

insert into Series (instituteId, title)
values(3, "DS");$

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


