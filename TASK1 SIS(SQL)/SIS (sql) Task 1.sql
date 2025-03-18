CREATE DATABASE SISDB;
USE SISDB;

-- Student table
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL
);
select * from students;
INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number)  
VALUES  
('Kirthi', 'Devi', '2004-02-29', 'skirthi2004@gmail.com', '9176768643'),  
('Shruthiy', 'Senthil', '2003-03-18', 'shruthi08@gmail.com', '9876543211'),  
('Kishore', 'Shiva', '2003-11-11', 'kish11@gmail.com', '9762124790'),  
('Kayal', 'Singaram', '2004-06-26', 'kayalsing03@gmail.com', '9778543213'),  
('Monish', 'Kumar', '2002-09-12', 'momo12@gmail.com', '2149876543'),  
('Kavya', 'V', '2003-01-22', 'kavya2201@gmail.com', '9876603215'),  
('Manu', 'Karthick', '2004-05-13', 'karthickmessi@gmail.com', '9874161213'),  
('Isabella', 'James', '2001-04-19', 'isabella@example.com', '9071743217'),  
('Aadhi', 'Rajan', '2002-12-20', 'adhi12@gmail.com', '9340912712'),  
('Selvi', 'Sethu', '2003-07-15', 'selvise0703@gmail.com', '9176533217');

select * from students;

-- Teacher table
CREATE TABLE teacher (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO teacher (first_name, last_name, email)  
VALUES  
('Anil', 'Kumar', 'anilkumar@gmail.com'),  
('Priya', 'S', 'priya11@hotmail.com'),  
('Rajesh', 'Verma', 'rajeshverma@gmail.com'),  
('Deepa', 'Nair', 'deepa.nair@gmail.com'),  
('Arun', 'Mohan', 'arunmohan123@hotmail.com'),  
('Sunita', 'A', 'sunita@gmail.com'),  
('Vikram', 'V', 'vikramviram@hotmail.com'),  
('Asha', 'Iral', 'ashairal0022@gmail.com'),  
('Karthik', 'Madhu', 'karthik.madhu@gmail.com'),  
('Lakshmi', 'Priya', 'lakshmi.priya1983@gmail.com');

select * from teacher;

-- Course table
CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    teacher_id INT,
    CONSTRAINT fk_teacher
        FOREIGN KEY (teacher_id)
        REFERENCES Teacher(teacher_id)
        ON DELETE SET NULL
);

INSERT INTO courses (course_name, credits, teacher_id)  
VALUES  
('Data Structures and Algorithms', 4, 1),  
('Operating Systems', 3, 2),  
('Database Management Systems', 3, 3),  
('Computer Networks', 3, 4),  
('Artificial Intelligence', 4, 5),  
('Machine Learning', 4, 6),  
('Web Development', 3, 7),  
('Software Engineering', 3, 8),  
('Cybersecurity', 3, 9),  
('Cloud Computing', 3, 10);

select * from courses;

-- Enrollment table 
CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    enrollment_date DATE NOT NULL,
    CONSTRAINT fk_student
        FOREIGN KEY (student_id)
        REFERENCES Students(student_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
        REFERENCES Courses(course_id)
        ON DELETE CASCADE
);

INSERT INTO enrollments (student_id, course_id, enrollment_date)
VALUES 
(3, 1, '2025-01-15'), 
(1, 2, '2025-01-16'), 
(2, 3, '2025-01-17'),
(5, 4, '2025-01-18'),
(4, 5, '2025-01-19'),
(1, 6, '2025-01-20'), 
(7, 7, '2025-01-21'),
(3, 8, '2025-01-22'), 
(6, 9, '2025-01-23'),
(2, 10, '2025-01-24'), 
(9, 1, '2025-01-25'),
(10, 2, '2025-01-26'),
(4, 3, '2025-01-27'), 
(8, 4, '2025-01-28'),
(7, 5, '2025-01-29'), 
(10, 6, '2025-01-30'),
(3, 7, '2025-02-01'), 
(5, 8, '2025-02-02'),
(9, 9, '2025-02-03'),
(1, 10, '2025-02-04');

select * from enrollments;

-- Payment table 
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    CONSTRAINT fk_payment_student
        FOREIGN KEY (student_id)
        REFERENCES Students(student_id)
        ON DELETE CASCADE
);

INSERT INTO payments (student_id, amount, payment_date)  
VALUES  
(3, 15000, '2025-01-16'),  
(1, 12000, '2025-01-17'), 
(2, 13000, '2025-01-18'),  
(5, 10000, '2025-01-19'),  
(4, 14000, '2025-01-20'),  
(1, 13500, '2025-01-21'),   
(7, 11000, '2025-01-22'),
(3, 15000, '2025-01-23'),  
(6, 12500, '2025-01-24'), 
(2, 12000, '2025-01-25'), 
(9, 15000, '2025-01-26'),  
(10, 12000, '2025-01-27'), 
(4, 13000, '2025-01-28'),  
(8, 10000, '2025-01-29'),
(7, 14000, '2025-01-30'),  
(10, 13500, '2025-01-31'), 
(3, 11000, '2025-02-02'), 
(5, 15000, '2025-02-03'), 
(9, 12500, '2025-02-04'),   
(1, 12000, '2025-02-05');

select * from payments;
