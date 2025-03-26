use sisdb;

/* 1.Write an SQL query to calculate the average number of students enrolled in each course. Use
aggregate functions and subqueries to achieve this*/
 
select c.course_id , c.course_name , 
(
select count(e.student_id)
from enrollments as e
where c.course_id=e.course_id
)as avg_student
from courses as c;

/* 2.Identify the student(s) who made the highest payment. Use a subquery to find the maximum
payment amount and then retrieve the student(s) associated with that amount*/
 
-- select student_id ,sum(amount) from payments group by student_id;
with student_total as 
(
select student_id ,sum(amount) as total_amount from payments group by student_id
)
select student_id,total_amount  
from student_total
where total_amount=(select max(total_amount) from student_total);

/* 3. Retrieve a list of courses with the highest number of enrollments. Use subqueries to find the
course(s) with the maximum enrollment count*/

with count_enroll as
(
select e.course_id , c.course_name ,count(e.student_id) as tot_enroll
from enrollments as e
join courses as c
on e.course_id=c.course_id
group by course_id
)

select course_id , course_name
from count_enroll
where tot_enroll in (select max(tot_enroll) from count_enroll);

/* 4.Calculate the total payments made to courses taught by each teacher. Use subqueries to sum
payments for each teacher's courses*/

select t.teacher_id, concat(t.first_name,' ',t.last_name) as name ,
(select sum(p.amount) from enrollments as e join payments as p on e.student_id=p.student_id where e.course_id in
(
select c.course_id
from courses as c
where c.teacher_id=t.teacher_id
))as tot_payments
from teacher as t;

/* 5. Identify students who are enrolled in all available courses. Use subqueries to compare a
student's enrollments with the total number of courses*/

select student_id
from enrollments
group by student_id
having count(course_id)=(select count(course_id) from courses);

/* 6.Retrieve the names of teachers who have not been assigned to any courses. Use subqueries to
find teachers with no course assignments*/
 
select teacher_id , concat(first_name,' ',last_name) as name
from teacher where teacher_id not in (
select distinct teacher_id from courses);

/* 7. Calculate the average age of all students. Use subqueries to calculate the age of each student
based on their date of birth*/

select avg(age) as avg_age
from(select timestampdiff(year,date_of_birth,curdate())as Age from students)as student_ages;

/*8.Identify courses with no enrollments. Use subqueries to find courses without enrollment
records.*/

select course_id , course_name 
from courses 
where course_id not in (select distinct course_id from enrollments);

/*9.Calculate the total payments made by each student for each course they are enrolled in. Use
subqueries and aggregate functions to sum payments*/

select s.student_id,concat(s.first_name,' ',s.last_name) as Name,
(select sum(amount) from payments as p where p.student_id=s.student_id) as tot_amount
from students as s;

/*10. Identify students who have made more than one payment. Use subqueries and aggregate
functions to count payments per student and filter for those with counts greater than one*/

select s.student_id , concat(s.first_name,' ',s.last_name) as Name , payment_count
from(select student_id , count(payment_id) as payment_count from payments group by student_id having count(payment_id)>1) as payment_summary
join students as s
on s.student_id=payment_summary.student_id;

/* 11.Write an SQL query to calculate the total payments made by each student. Join the "Students"
table with the "Payments" table and use GROUP BY to calculate the sum of payments for each
student*/

select concat(s.first_name,' ',s.last_name) as Name, sum(p.amount) as paid
from students as s 
left join payments as p
on s.student_id=p.student_id
group by s.student_id
order by s.first_name;

/* 12.Retrieve a list of course names along with the count of students enrolled in each course. Use
JOIN operations between the "Courses" table and the "Enrollments" table and GROUP BY to
count enrollments*/

select c.course_name , count(e.student_id) as tot_count
from courses as c
left join enrollments as e
on c.course_id=e.course_id
group by c.course_id;

/* 13. Calculate the average payment amount made by students. Use JOIN operations between the
"Students" table and the "Payments" table and GROUP BY to calculate the average*/

-- Query for overall average
-- select avg(amount) as overall_avg from payments;

select concat(s.first_name, ' ', s.last_name) AS Name, avg(p.amount) as avg_amt
from students as s
join payments as p
on s.student_id=p.student_id
group by s.student_id;
