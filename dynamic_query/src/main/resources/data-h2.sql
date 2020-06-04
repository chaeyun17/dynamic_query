insert into team (id, `name`) values (1, 'team1');
insert into team (id, `name`) values (2, 'team2');

insert into member (id, `name`, team_id) values 
(1, 'mem1', 1), 
(2, 'mem2', 1),
(3, 'mem3', 2), 
(4, 'mem4', 2);