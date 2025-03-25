-- 1. Select all open incidents
select * from crime;
select * from crime where status='open';

-- 2. Find the total number of incidents
select count(*) as TotalIncidents from crime;

-- 3. List all unique incident types
select distinct(incidentType) from crime;

-- 4. Retrieve incidents that occurred between '2023-09-01' and '2023-09-10'
select * from crime where IncidentDate between '2023-09-01' and '2023-09-10';

-- 5. List persons involved in incidents in descending order of age
alter table victim add age int;
alter table suspect add age int;

-- update the victim 
update victim set age = 35 where victimid = 1;
update victim set age = 28 where victimid = 2;
update victim set age = 42 where victimid = 3;
update victim set age = 30 where victimid = 4;
update victim set age = 25 where victimid = 5;
update victim set age = 40 where victimid = 6;
update victim set age = 33 where victimid = 7;
update victim set age = 29 where victimid = 8;
select * from victim;

-- update the suspect 
update suspect set age = 38 where suspectid = 1;
update suspect set age = 45 where suspectid = 2;
update suspect set age = 27 where suspectid = 3;
update suspect set age = 50 where suspectid = 4;
update suspect set age = 32 where suspectid = 5;
update suspect set age = 41 where suspectid = 6;
select * from suspect;
 
-- only victims
select name, age from victim order by age desc;

-- only suspect
select name, age from suspect order by age desc;

-- both victim and suspect
select name,age,'victim' as role from victim
union
select name,age,'suspect' as role from suspect
order by age desc;

-- 6. Find the average age of persons involved in incidents
select avg(age) as avg_age from 
(
select age from victim
union all
select age from suspect
)as tot_person;

-- 7. List incident types and their counts, only for open cases.
select * from crime;
select incidenttype,count(*) as incidentCount
from crime
where status='open'
group by incidenttype;

-- 8. Find persons with names containing 'Doe'
select name from victim where name like '%Doe%';
select name from suspect where name like '%Doe%';

-- 9. Retrieve the names of persons involved in open cases and closed cases
select v.name , 'victim' as role , c.status
from victim as v
join crime as c
on v.crimeid=c.crimeid
where c.status='open' or c.status='closed'
union all 
select s.name , 'suspect' as role,c.status
from suspect as s
join crime as c
on s.crimeid=c.crimeid
where status='open' or c.status='closed';

-- 10. List incident types where there are persons(victim and suspect) aged 30 or 35 involved
select distinct incidenttype
from crime as c
join victim as v
on c.crimeid=v.crimeid
where v.age in(30,35)
union
select distinct incidenttype
from crime as c
join suspect as s
on c.crimeid=s.crimeid
where s.age in (30,35);