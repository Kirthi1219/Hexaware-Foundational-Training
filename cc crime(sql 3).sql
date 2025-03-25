-- 11. Find persons involved in incidents of the same type as 'Robbery'
select v.name,'victim' as role, c.incidenttype 
from victim v 
join crime c on v.crimeid = c.crimeid 
where c.incidenttype='Robbery'
union all
select s.name,'suspect' as role, c.incidenttype 
from suspect s 
join crime c on s.crimeid = c.crimeid 
where c.incidenttype='Robbery';

-- 12. . List incident types with more than one open case
select incidenttype,count(*) as open
from crime 
where status='open'
group by incidenttype
having count(*)>1;

-- 13.List all incidents with suspects whose names also appear as victims in other incidents
select c.crimeid,c.incidenttype,s.name 
from suspect as s
join crime as c
on s.crimeid=c.crimeid
where s.name in(select v.name from victim as v where v.crimeid<>s.crimeid);

insert into victim (victimid, crimeid, name, contactinfo, injuries, age)
values (9, 4, 'Robber 1', 'robber1@example.com', 'Minor injuries', 38);

-- 14.. Retrieve all incidents along with victim and suspect details
select c.crimeid, c.incidenttype, c.incidentdate, c.location, v.name as victim_name, v.contactinfo, v.injuries, s.name as suspect_name, s.description as suspect_description
from crime c
left join victim v
on c.crimeid = v.crimeid
left join suspect s 
on c.crimeid = s.crimeid
order by c.crimeid;

-- 15.Find incidents where the suspect is older than any victim
select c.crimeid,c.incidenttype,s.name as suspect_name, s.age as suspect_age,v.name as victim_name, v.age as victim_age
from crime c
join suspect s 
on c.crimeid = s.crimeid
join victim v 
on c.crimeid = v.crimeid
where s.age > all (select age from victim where crimeid = c.crimeid);

-- 16. Find suspects involved in multiple incidents
select * from suspect;
select name, count(distinct crimeid) as incident_count
from suspect
group by name
having count(distinct crimeid) > 1;

-- Inserting a record in suspect table
insert into suspect (suspectid, crimeid, name, description, criminalhistory, age)
values (7, 2, 'Robber 1', 'Repeat offender', 'Prior robbery cases', 38);

-- 17. . List incidents with no suspects involved
select  c.crimeid, c.incidenttype, c.incidentdate, c.status , s.name as suspectName 
from crime as c
left join suspect as s
on c.crimeid=s.crimeid
where s.Suspectid is null;

select * from crime;

insert into crime (crimeid, incidenttype, incidentdate, location, description, status)  
values (7, 'Money Laundering', '2023-11-05', '555 Finance St, Capital City', 'Investigation into illegal money transfers', 'closed');

/* 18.List all cases where at least one incident is of type 'Homicide' and all other incidents are of type
'Robbery'*/
select c1.crimeid,c1.incidenttype
from crime as c1
where c1.incidenttype='homicide'
and not exists(select 1 from crime as c2 where c2.crimeid=c1.crimeid and c2.IncidentType<>'homicide' and c2.IncidentType<>'robbery');

/* 19. Retrieve a list of all incidents and the associated suspects, showing suspects for each incident, or
'No Suspect' if there are none*/
select c.crimeid,c.incidenttype,coalesce(s.name,'No Suspect') as suspectname
from crime as c
left join suspect as s
on c.crimeid=s.crimeid;

/* 20.List all suspects who have been involved in incidents with incident types 'Robbery' or 'Assault'*/
select s.suspectid,s.name,c.incidenttype
from suspect as s
join crime as c
on s.crimeid=c.crimeid
where c.IncidentType='robbery' or c.IncidentType='assault';