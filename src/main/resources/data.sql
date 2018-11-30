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
values ("255 Data Mining", 3, "Data representation and preprocessing, proximity, finding nearest neighbors, dimensionality reduction, exploratory analysis, association analysis and sequential patterns, supervised inference and prediction, classification, regression, model selection and evaluation, overfitting, clustering, advanced topics.");$
insert into Course (title, instructorId, description)
values ("256 Large-Scale Analytics", 3, "Data mining and machine learning algorithms and applications for large amounts of data. Information retrieval and search engines, social network analysis, link analysis, ranking, Web personalization, recommender systems, opinion mining and sentiment analysis, advanced topics.");$
insert into Course (title, instructorId, description)
values ("257 Machine Learning", 4, "Machine Learning concept, feasibility and learning types, theory of generalization, bias and variance, linear models for classification and regression, nonlinear transformation, regularization and validation, kernel methods, radial basis functions, support vector machines, ensemble methods, neural networks, hands-on projects. Prerequisite: Instructor Consent");$
insert into Course (title, instructorId, description)
values ("258 Deep Learning", 4, "Deep neural networks and their applications to various problems, e.g., speech recognition, image segmentation, and natural language processing. Covers underlying theory, the range of applications to which it has been applied, and learning from very large data sets. Prerequisite: CMPE 255 or CMPE 257 or instructor consent");$
insert into Course (title, instructorId, description)
values ("226 Database Systems", 4, "Database architectures, technologies, and practices for enterprise systems that use structured, semi-structured, and unstructured data. Provides opportunities to research and acquire experience using modern and emerging concepts in relational and non-relational database theory and technologies. Prerequisite: CMPE 272 or instructor consent. Computer Engineering or Software Engineering majors only.");$
insert into Course (title, instructorId, description)
values ("207 Network Programming and Application", 4, "Development and implementation of networking software for building distributed applications. Application Programming Interfaces: BSD Sockets, Winsock, Remote Procedure Call and Middleware. Network programming project. Prerequisite: CMPE 206, or instructor consent.");$
insert into Course (title, instructorId, description)
values ("266 Big Data Engineering and Analytics", 4, "New big data related technologies, architecture, tool, algorithms and analytics to manage and extract values and hidden knowledge from data whose scale, diversity, speed and complexity are big. Prerequisite: CMPE 272 or Instructor Consent");$


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
insert into Enroll (studentId, courseId)
values(1, 2);$
insert into Enroll (studentId, courseId)
values(1, 3);$
insert into Enroll (studentId, courseId)
values(2, 1);$
insert into Enroll (studentId, courseId)
values(2, 5);$


