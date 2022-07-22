insert into departments(id, name) 
values 
(1, 'departmentA'),
(2, 'departmentB'),
(3, 'departmentC');
alter table departments alter column id restart with 4;
