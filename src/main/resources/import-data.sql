-- AppUser (username, password, appRole)
INSERT INTO AppUser (username, password, appRole)
VALUES ("student1", "student1", 0);
INSERT INTO AppUser (username, password, appRole)
VALUES ("student2", "student2", 0);

INSERT INTO AppUser (username, password, appRole)
VALUES ("SJSU_T1", "SJSU_T1", 1);
INSERT INTO AppUser (username, password, appRole)
VALUES ("SJSU_T2", "SJSU_T2", 1);

INSERT INTO AppUser (username, password, appRole)
VALUES ("SCU_T1", "SCU_T1", 1);
INSERT INTO AppUser (username, password, appRole)
VALUES ("SCU_T2", "SCU_T2", 1);

INSERT INTO AppUser (username, password, appRole)
VALUES ("SJSU", "SJSU", 2);
INSERT INTO AppUser (username, password, appRole)
VALUES ("SCU", "SCU", 2);

INSERT INTO AppUser (username, password, appRole)
VALUES ("admin1", "admin1", 3);

-- WorkFor (instructorId, instituteId)
-- Work for SJSU
INSERT INTO WorkFor (instructorId, instituteId)
VALUES (3, 7);
INSERT INTO WorkFor (instructorId, instituteId)
VALUES (4, 7);

-- Work for SCU
INSERT INTO WorkFor (instructorId, instituteId)
VALUES (5, 8);
INSERT INTO WorkFor (instructorId, instituteId)
VALUES (6, 8);

-- Course (id, title, instructorId)
INSERT INTO Course (title, instructorId, description)
VALUES ("255 Data Mining", 3,
        "Data representation and preprocessing, proximity, finding nearest neighbors, dimensionality reduction, exploratory analysis, association analysis and sequential patterns, supervised inference and prediction, classification, regression, model selection and evaluation, overfitting, clustering, advanced topics.");
INSERT INTO Course (title, instructorId, description)
VALUES ("256 Large-Scale Analytics", 3,
        "Data mining and machine learning algorithms and applications for large amounts of data. Information retrieval and search engines, social network analysis, link analysis, ranking, Web personalization, recommender systems, opinion mining and sentiment analysis, advanced topics.");
INSERT INTO Course (title, instructorId, description)
VALUES ("257 Machine Learning", 4,
        "Machine Learning concept, feasibility and learning types, theory of generalization, bias and variance, linear models for classification and regression, nonlinear transformation, regularization and validation, kernel methods, radial basis functions, support vector machines, ensemble methods, neural networks, hands-on projects. Prerequisite: Instructor Consent");
INSERT INTO Course (title, instructorId, description)
VALUES ("258 Deep Learning", 4,
        "Deep neural networks and their applications to various problems, e.g., speech recognition, image segmentation, and natural language processing. Covers underlying theory, the range of applications to which it has been applied, and learning from very large data sets. Prerequisite: CMPE 255 or CMPE 257 or instructor consent");
INSERT INTO Course (title, instructorId, description)
VALUES ("226 Database Systems", 4,
        "Database architectures, technologies, and practices for enterprise systems that use structured, semi-structured, and unstructured data. Provides opportunities to research and acquire experience using modern and emerging concepts in relational and non-relational database theory and technologies. Prerequisite: CMPE 272 or instructor consent. Computer Engineering or Software Engineering majors only.");
INSERT INTO Course (title, instructorId, description)
VALUES ("207 Network Programming and Application", 4,
        "Development and implementation of networking software for building distributed applications. Application Programming Interfaces: BSD Sockets, Winsock, Remote Procedure Call and Middleware. Network programming project. Prerequisite: CMPE 206, or instructor consent.");
INSERT INTO Course (title, instructorId, description)
VALUES ("266 Big Data Engineering and Analytics", 4,
        "New big data related technologies, architecture, tool, algorithms and analytics to manage and extract values and hidden knowledge from data whose scale, diversity, speed and complexity are big. Prerequisite: CMPE 272 or Instructor Consent");


-- Homework for 255
INSERT INTO Homework(courseId, title, content, type)
VALUES (1, "255 Data Mining HW1", "Medical Text classification", 0);
INSERT INTO Homework(courseId, title, content, type)
VALUES (1, "255 Data Mining HW2", "Feature engineer and reduction", 0);
INSERT INTO Homework(courseId, title, content, type)
VALUES (1, "255 Data Mining EXAM", "Final Exam", 1);

-- Material for 255
INSERT INTO Material(courseId, title, url)
VALUES (1, "255 week1", "255 slide 1");
INSERT INTO Material(courseId, title, url)
VALUES (1, "255 week2", "255 slide 2");
INSERT INTO Material(courseId, title, url)
VALUES (1, "255 week3", "255 slide 3");

-- Material for 256
INSERT INTO Material(courseId, title, url)
VALUES (2, "256 week3", "256 slide 1");

INSERT INTO Discussion(userId, courseId, title, content)
VALUES (3, 1, "instructor post", "aaaaaaa");
INSERT INTO Discussion(userId, courseId, title, content)
VALUES (1, 1, "student post", "babaabab");

INSERT INTO Series (instituteId, title)
VALUES (7, "DS");

INSERT INTO CourseSeries (courseId, seriesId)
VALUES (1, 1);
INSERT INTO CourseSeries (courseId, seriesId)
VALUES (2, 1);
INSERT INTO CourseSeries (courseId, seriesId)
VALUES (3, 1);

-- Enroll (studentId, courseId, isCompleted, isDropped)
INSERT INTO Enroll (studentId, courseId)
VALUES (1, 1);
INSERT INTO Enroll (studentId, courseId)
VALUES (1, 2);
INSERT INTO Enroll (studentId, courseId)
VALUES (1, 3);
INSERT INTO Enroll (studentId, courseId)
VALUES (2, 1);
INSERT INTO Enroll (studentId, courseId)
VALUES (2, 5);

INSERT INTO Rating (studentId, courseId, rate)
VALUES (1, 1, 5);
INSERT INTO Rating (studentId, courseId, rate)
VALUES (2, 1, 4);



