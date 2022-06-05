create table employees (
id bigint auto_increment primary key,
name varchar(100),
surname varchar(100),
department varchar(100)
);

create table schedules (
    id bigint auto_increment primary key,
    employee_id bigint,
    schedule_year int,
    schedule_month int,
    schedule_day int,
    start_of_work int,
    end_of_work int,
    time_of_work int
);

create view schedules_group 
as 
select employee_id, schedule_year, schedule_month
from schedules
group by employee_id, schedule_year, schedule_month;



