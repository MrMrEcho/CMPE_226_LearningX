use LearningX;

-- AppUser (username, password, appRole)
insert into AppUser (username, password, appRole)
values ("n1", "p1", 0);
insert into AppUser (username, password, appRole)
values ("n2", "p1", 0);
insert into AppUser (username, password, appRole)
values ("n3", "p1", 1);
insert into AppUser(username, password, appRole)
values ("n4", "p1", 2);
insert into AppUser (username, password, appRole)
values ("n5", "p1", 3);

-- Course (id, title, instructorId)
insert into Course (title, instructorId)
values ("T1", 1);
insert into Course (title, instructorId)
values ("T2", 1);
insert into Course (title, instructorId)
values ("T3", 1);
insert into Course (title, instructorId)
values ("T4", 1);
insert into Course (title, instructorId)
values ("T5", 1);

-- WorkFor (instructorId, instituteId)
insert into WorkFor (instructorId, instituteId)
values(3, 4);

-- Series (id, instituteId, title)
insert into Series (instituteId, title)
values(4, "CS");
insert into Series (instituteId, title)
values(4, "SE");

-- CourseSeries (courseId, seriesId)
insert into CourseSeries (courseId, seriesId)
values(1, 1);
insert into CourseSeries (courseId, seriesId)
values(2, 2);
insert into CourseSeries (courseId, seriesId)
values(3, 1);
insert into CourseSeries (courseId, seriesId)
values(4, 2);
insert into CourseSeries (courseId, seriesId)
values(5, 1);

-- Enroll (studentId, courseId, isCompleted, isDropped)
insert into Enroll (studentId, courseId)
values(1, 1);
insert into Enroll (studentId, courseId)
values(2, 1);
insert into Enroll (studentId, courseId)
values(3, 1);
insert into Enroll (studentId, courseId)
values(4, 1);
insert into Enroll (studentId, courseId)
values(4, 2);
insert into Enroll (studentId, courseId)
values(5, 2);

-- Rating (studentId, courseId, rating)
insert into Rating (studentId, courseId, rating)
values(1, 1, 5);
insert into Rating (studentId, courseId, rating)
values(2, 1, 3);
insert into Rating (studentId, courseId, rating)
values(3, 1, 6);
insert into Rating (studentId, courseId, rating)
values(4, 1, 1);
insert into Rating (studentId, courseId, rating)
values(4, 2, 6);
insert into Rating (studentId, courseId, rating)
values(5, 2, 9);

-- Material (id, courseId, title, url)
insert into Material (courseId, title, url)
values(1, "M1", "U1");
insert into Material (courseId, title, url)
values(2, "M1", "U1");
insert into Material (courseId, title, url)
values(3, "M1", "U1");
insert into Material (courseId, title, url)
values(4, "M1", "U1");
insert into Material (courseId, title, url)
values(5, "M1", "U1");

-- Homework (id, courseId, title, content, type)
insert into Homework (courseId, title, content, type)
values(1, "Java Practice 1", "print string", 0);
insert into Homework (courseId, title, content, type)
values(1, "Java Practice 2", "sort string", 0);
insert into Homework (courseId, title, content, type)
values(1, "Java Exam", "print and sort string", 1);

-- Submission (studentId, homeworkId, answer, grade, isGraded)
insert into Submission (studentId, homeworkId, answer)
values(1, 1, "a1");
insert into Submission (studentId, homeworkId, answer)
values(1, 2, "a1");
insert into Submission (studentId, homeworkId, answer)
values(2, 2, "a1");
insert into Submission (studentId, homeworkId, answer)
values(2, 3, "a1");
insert into Submission (studentId, homeworkId, answer)
values(3, 3, "a1");

update Submission
set grade = 80, isGraded=true where studentId = 1 and homeworkId = 1;
update Submission
set grade = 90, isGraded=true where studentId = 1 and homeworkId = 2;
update Submission
set grade = 80, isGraded=true where studentId = 2 and homeworkId = 2;
update Submission
set grade = 50, isGraded=true where studentId = 2 and homeworkId = 3;
update Submission
set grade = 80, isGraded=true where studentId = 3 and homeworkId = 3;

-- Discussion (userId, courseId, title, content)
insert into Discussion (userId, courseId, title, content)
values(1, 1, "D1", "C1");
insert into Discussion (userId, courseId, title, content)
values(2, 1, "D1", "C1");
insert into Discussion (userId, courseId, title, content)
values(2, 2, "D1", "C1");
insert into Discussion (userId, courseId, title, content)
values(3, 2, "D1", "C1");