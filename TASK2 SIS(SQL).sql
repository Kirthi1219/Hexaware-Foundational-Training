-- TASK 2
use sisdb;

-- 1
INSERT INTO students(first_name,last_name,date_of_birth,email,phone_number)
VALUES('John', 'Doe', '1995-08-15', 'john.doe@example.com', '1234567890');
select * from students;

-- 2
INSERT INTO enrollments(student_id,course_id,enrollment_date)
VALUES(11,3,'2025-02-04');
select * from enrollments;

-- 3
UPDATE teacher
SET email='deepanair14@hotmail.com'
WHERE teacher_id=4;
select * from teacher;

-- 4
DELETE FROM enrollments
WHERE student_id=3 AND course_id=8;
select * from enrollments;

-- 5
update courses 
set teacher_id=5
where course_id=6;
select * from courses;

-- 6
select * from students;
delete from students
where student_id=5;
select * from enrollments where student_id=5; -- check 

-- 7 
select * from payments;
insert into payments(student_id,amount,payment_date)
values(11,12000,'2025-02-05');

update payments 
set amount=13000
where payment_id=21;