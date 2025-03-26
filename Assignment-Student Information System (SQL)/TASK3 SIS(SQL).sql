-- TASK 3
use sisdb;

-- 1
select s.student_id,CONCAT(s.first_name, ' ', s.last_name) as Name , SUM(p.amount) as tot_payment
from students as s
join payments as p
on s.student_id = p.student_id
group by s.student_id;

-- 2 
select c.course_id , c.course_name , COUNT(e.student_id) as student_count
from courses as c
join enrollments as e
on c.course_id=e.course_id
group by c.course_id;

-- 3
insert into students (first_name, last_name ,date_of_birth,email,phone_number)
values
('Ravi', 'Shankar', '2003-05-12', 'ravi.shankar@gmail.com', '9123456789'),  
('Ananya', 'pinky', '2002-09-21', 'pinky00@hotmail.com', '9876541230');
select * from students;

select CONCAT(s.first_name, ' ', s.last_name) as Name
from students as s
left join enrollments as e
on s.student_id=e.student_id
where e.enrollment_id is null;

-- 4 
select s.first_name , s.last_name , c.course_name 
from students as s 
join enrollments as e
on s.student_id=e.student_id
join courses as c
on e.course_id=c.course_id
order by s.first_name;

-- 5 
select CONCAT(t.first_name,' ',t.last_name)as Name, c.course_name 
from teacher as t
left join courses as c
on t.teacher_id=c.teacher_id
order by t.first_name;

-- 6 
select CONCAT(s.first_name,' ',s.last_name)as Name , c.course_name , e.enrollment_date
from students as s
left join enrollments as e
on s.student_id=e.student_id
left join courses as c
on e.course_id=c.course_id;
-- order by s.first_name;

-- 7
select CONCAT(s.first_name,' ',s.last_name)as Name 
from students as s
left join payments as p
on s.student_id=p.student_id
where payment_id is null;

-- 8 
select c.course_id, c.course_name
from courses as c
left join enrollments as e
on c.course_id=e.course_id
where e.enrollment_id is null;

-- 9
select distinct(e1.student_id),concat(s.first_name,' ',s.last_name) as Name
from enrollments as e1
join enrollments as e2
on e1.student_id=e2.student_id and e1.course_id<>e2.course_id
join students as s
on e1.student_id=s.student_id;

-- 10
select CONCAT(t.first_name,' ',t.last_name)as Name
from teacher as t
left join courses as c
on t.teacher_id=c.teacher_id
where course_id is null;