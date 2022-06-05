
insert into employees (id, name, surname, department) 
values 
(1, 'Jan', 'Kowalski', 'ProdukcjaA'),
(2, 'Adam', 'Nowak', 'ProdukcjaA'),
(3, 'Zbigniew', 'Wawszyn', 'Diagnostyka');

insert into schedules(id, employee_id, schedule_year, schedule_month, 
schedule_day, start_of_work, end_of_work, time_of_work)
values 
(1, 1, 2022, 1, 1, 420, 900, 480),
(2, 1, 2022, 1, 2, 420, 900, 480),
(3, 2, 2022, 1, 1, 420, 900, 480),
(4, 3, 2022, 1, 2, 420, 900, 480),
(5, 3, 2022, 1, 3, 420, 900, 480),
(6, 3, 2022, 1, 4, 420, 900, 480),
(7, 3, 2022, 1, 5, 420, 900, 480),
(8, 3, 2022, 1, 6, 420, 900, 480),
(9, 1, 2022, 2, 1, 420, 900, 480);
