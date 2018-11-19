drop database if exists LearningX;
create database LearningX;
use LearningX;

    create table AppUser
    (
    User_name		char(10),
	User_password	varchar(50),
	User_type	varchar(10),
	Email	varchar(50),
    primary key (User_name)

    );

    create table Institute
    (
    Institute_id		char(10),
	Name		varchar(50),
    primary key (Institute_id)
    
    );
    
	create table Course
	(
    Course_id		char(10),
    Institute_id		char(10),
	Name		varchar(50),
    Session	varchar(20), 
    primary key (Course_id),
	foreign key(Institute_id) references Institute(Institute_id)

    
	);
    
	create table Prequisites(
		Course_id		char(10),
        Prequisite		varchar(50),
        primary key (Course_id),
		foreign key(Course_id) references Course(Course_id)
    );
    
        create table CourseSeries
	(
	CourseSeries_id 	char(10),
    Institute_id		char(10),
    Name		varchar(50),
    primary key (CourseSeries_id),
	foreign key(Institute_id) references Institute(Institute_id)

	); 
    
    create table Contain
    (
		Course_id		varchar(10),
		CourseSeries_id 	char(10),
        primary key(Course_id, CourseSeries_id),
		foreign key(Course_id) references Course(Course_id) on delete cascade,
        foreign key(CourseSeries_id) references CourseSeries(CourseSeries_id) on delete cascade

    );
    
	create table Instructor
    (
    Instructor_id		char(10),
    Institute_id		char(10),
    Start_date	date,
    Name		varchar(50),
    primary key(Instructor_id),
	foreign key(Institute_id) references Institute(Institute_id) on delete cascade

    );
    
	create table Taught_by
    (
		Instructor_id		char(10),
        Course_id		char(10),
        primary key(Instructor_id,Course_id),
        foreign key(Course_id) references Course(Course_id) on delete cascade,
		foreign key(Instructor_id) references Instructor(Instructor_id) on delete cascade
    );
    
    
        create table Material
    (
		Course_id		char(10),
		Material_id		char(10),
        Instructor_id		char(10),
        URL		varchar(8000),
        Type	varchar(20),
        Content		varchar(8000),
        primary key(Course_id,Material_id),
        foreign key(Course_id) references Course(Course_id) on delete cascade,
		foreign key(Instructor_id) references Instructor(Instructor_id)

        
    );
    
    
	create table HomeworkExam
    (
		Course_id		char(10),
		Work_id		char(10),
        Due_date		date,
        Content			varchar(8000),
        primary key(Work_id,Course_id),
        
		foreign key(Course_id) references Course(Course_id) on delete cascade
        
    );
    
	create table Student
	(
    Student_id		char(10),
	Name		varchar(50),
    primary key (Student_id)
	);
    
	CREATE TABLE Submission (
    Work_id		char(10),
    Student_id CHAR(10),
    Instructor_id CHAR(10),
    Grade INT,
    PRIMARY KEY (Work_id,Student_id , Instructor_id),
    
    FOREIGN KEY (work_id)
        REFERENCES HomeworkExam (work_id)
        ON DELETE CASCADE,
        
    FOREIGN KEY (Student_id)
        REFERENCES Student (Student_id)
        ON DELETE CASCADE,
        
    FOREIGN KEY (Instructor_id)
        REFERENCES Instructor (Instructor_id)
        ON DELETE CASCADE
);

	create table Rate(
    
    Course_id		char(10),
    Student_id		char(10),
    Rate		int,
    Date		date,
    primary key(Course_id,Student_id),
	foreign key(Course_id) references Course(Course_id) on delete cascade,
    foreign key(Student_id) references Student(Student_id) on delete cascade
    );
    
create table Enroll(
    
    Course_id		char(10),
    Student_id		char(10),

    primary key(Course_id,Student_id),
	foreign key(Course_id) references Course(Course_id) on delete cascade,
    foreign key(Student_id) references Student(Student_id) on delete cascade
    );
    
    

create table Question
	(
    
    Question_id		char(10),
    Course_id		char(10),
    Student_id		char(10),
    Instructor_id	char(10),
    Title		varchar(50),
	 Content		varchar(280),
	 Post_date		date,
     Reply		varchar(840),
     Reply_date		date,
	 primary key (Course_id,Question_id),
	 foreign key(Course_id) references Course(Course_id) on delete cascade,
	 foreign key(Student_id) references Student(Student_id),
	 foreign key(Instructor_id) references Instructor(Instructor_id)


	);
