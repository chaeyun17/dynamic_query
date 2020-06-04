create table team (
   id bigint not null,
	name varchar(255),
	primary key (id)
);

create table member (
   id bigint not null,
	name varchar(255),
	team_id bigint,
	primary key (id)
);

alter table member 
   add constraint FKcjte2jn9pvo9ud2hyfgwcja0k 
   foreign key (team_id) 
   references team;