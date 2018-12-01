-- DELIMITER ;$

DROP TABLE IF EXISTS Discussion;$
DROP TABLE IF EXISTS Submission;$
DROP TABLE IF EXISTS Homework;$
DROP TABLE IF EXISTS Material;$
DROP TABLE IF EXISTS Rating;$
DROP TABLE IF EXISTS Enroll;$
DROP TABLE IF EXISTS CourseSeries;$
DROP TABLE IF EXISTS Course;$
DROP TABLE IF EXISTS Series;$
DROP TABLE IF EXISTS WorkFor;$
DROP TABLE IF EXISTS AppUser;$

CREATE TABLE AppUser
(
    id       INT AUTO_INCREMENT,
    username VARCHAR(32)  NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    appRole  INT,

    PRIMARY KEY (id),

    FULLTEXT INDEX user_name (username)
);$

CREATE TABLE Course
(
    id           INT AUTO_INCREMENT,
    title        VARCHAR(128) NOT NULL UNIQUE,
    description  VARCHAR(1024),
    instructorId INT,

    PRIMARY KEY (id),

    FOREIGN KEY (instructorId) REFERENCES AppUser (id)
        ON DELETE CASCADE,

    FULLTEXT INDEX course_content (title, description),
    FULLTEXT INDEX course_title (title),
    FULLTEXT INDEX course_description (description)
);$

CREATE TABLE WorkFor
(
    instructorId INT,
    instituteId  INT,

    PRIMARY KEY (instructorId, instituteId),

    FOREIGN KEY (instructorId) REFERENCES AppUser (id)
        ON DELETE CASCADE,
    FOREIGN KEY (instituteId) REFERENCES AppUser (id)
        ON DELETE CASCADE
);$

CREATE TABLE Series
(
    id          INT AUTO_INCREMENT,
    instituteId INT          NOT NULL,
    title       VARCHAR(128) NOT NULL,
    description VARCHAR(1024),

    PRIMARY KEY (id),

    FOREIGN KEY (instituteId) REFERENCES AppUser (id)
        ON DELETE CASCADE
);$

CREATE TABLE CourseSeries
(
    courseId INT NOT NULL,
    seriesId INT NOT NULL,

    PRIMARY KEY (courseId, seriesId),

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE,

    FOREIGN KEY (seriesId) REFERENCES Series (id)
        ON DELETE CASCADE
);$

CREATE TABLE Enroll
(
    studentId   INT NOT NULL,
    courseId    INT NOT NULL,
    hasCompleted BOOLEAN DEFAULT FALSE,
    hasDropped   BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (studentId, courseId),

    FOREIGN KEY (studentId) REFERENCES AppUser (id)
        ON DELETE CASCADE,

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE
);$

CREATE TABLE Rating
(
    studentId INT NOT NULL,
    courseId  INT NOT NULL,
    rate      INT DEFAULT 0,

    PRIMARY KEY (studentId, courseId),

    FOREIGN KEY (studentId) REFERENCES AppUser (id)
        ON DELETE CASCADE,

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE
);$

CREATE TABLE Material
(
    id       INT AUTO_INCREMENT,
    courseId INT          NOT NULL,
    title    VARCHAR(128) NOT NULL,
    url      VARCHAR(1024),

    PRIMARY KEY (id),

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE
);$

CREATE TABLE Homework
(
    id       INT AUTO_INCREMENT,
    courseId INT          NOT NULL,
    title    VARCHAR(128) NOT NULL,
    content  VARCHAR(256),
    type     INT          NOT NULL,

    PRIMARY KEY (id),

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE
);$

CREATE TABLE Submission
(
    studentId  INT NOT NULL,
    homeworkId INT NOT NULL,
    answer     VARCHAR(1024),
    grade      INT     DEFAULT 0,
    hasGrade   BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (studentId, homeworkId),

    FOREIGN KEY (studentId) REFERENCES AppUser (id)
        ON DELETE CASCADE,

    FOREIGN KEY (homeworkId) REFERENCES Homework (id)
        ON DELETE CASCADE
);$

CREATE TABLE Discussion
(
    id       INT AUTO_INCREMENT,
    userId   INT          NOT NULL,
    courseId INT          NOT NULL,
    title    VARCHAR(128) NOT NULL,
    content  VARCHAR(1024),

    -- primary key (userId, courseId),
    PRIMARY KEY (id),

    FOREIGN KEY (userId) REFERENCES AppUser (id)
        ON DELETE CASCADE,

    FOREIGN KEY (courseId) REFERENCES Course (id)
        ON DELETE CASCADE
);$

-- Create Views
CREATE OR REPLACE VIEW Student
AS
SELECT *
FROM AppUser
WHERE appRole = 0;$

CREATE OR REPLACE VIEW Instructor
AS
SELECT *
FROM AppUser
WHERE appRole = 1;$

CREATE OR REPLACE VIEW Institute
AS
SELECT *
FROM AppUser
WHERE appRole = 2;$

CREATE OR REPLACE VIEW AdminUser
AS
SELECT *
FROM AppUser
WHERE appRole = 3;$

CREATE OR REPLACE VIEW AverageRating
AS
SELECT C.id AS id, C.title AS title, avg(R.rate) AS rate, count(DISTINCT R.studentId) AS count
FROM Course C
         JOIN Rating R ON C.id = R.courseId
GROUP BY C.id;$

-- Create stored procedures
DROP FUNCTION IF EXISTS isExam ;$
CREATE FUNCTION `isExam`(homeworkId INT)
    RETURNS BOOLEAN
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE htype INT;
    SELECT type INTO htype FROM Homework WHERE id = homeworkId;
    IF htype = 1 THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END ;$


DROP FUNCTION IF EXISTS getCourseIdByHomeworkId ;$
CREATE FUNCTION `getCourseIdByHomeworkId`(homeworkId INT)
    RETURNS INT
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE cid INT;
    SELECT courseId INTO cid FROM Homework WHERE id = homeworkId;
    RETURN cid;
END ;$


DROP PROCEDURE IF EXISTS completeEnroll ;$
CREATE PROCEDURE `completeEnroll`(studentId INT, cid INT)
BEGIN
    UPDATE Enroll E
    SET hasCompleted = TRUE
    WHERE E.studentId = studentId
      AND E.courseId = cid;
END ;$

-- Create triggers

DROP TRIGGER IF EXISTS AfterSubmissionGraded ;$
CREATE TRIGGER `AfterSubmissionGraded`
    AFTER UPDATE
    ON Submission
    FOR EACH ROW
BEGIN
    DECLARE cid INT;
    IF NEW.hasGrade AND NEW.grade >= 60 AND isExam(NEW.homeworkId) THEN
        SET cid = getCourseIdByHomeworkId(NEW.homeworkId);
        CALL completeEnroll(NEW.studentId, cid);
    END IF;
END ;$

-- DELIMITER ;

