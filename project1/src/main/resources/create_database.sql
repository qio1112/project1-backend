drop database if exists website;

create database if not exists website;

use website;

SET FOREIGN_KEY_CHECKS = 0;
drop table if exists `resource`;
drop table if exists `data`;
drop table if exists `formula_page_data`;
drop table if exists `formulas`;
drop table if exists `projects`;
drop table if exists `users`;
SET FOREIGN_KEY_CHECKS = 1;

create table `users` (
    `username` varchar(50) primary key,
    `password` varchar(50) not null,
    `name` varchar(50) not null,
    `member_date` date not null
) engine=innodb;


create table `projects` (
	`id` int auto_increment primary key,
    `name` varchar(50) not null
) engine=innodb;

create table `data` (
	`project_id` int not null,
    `resource_code` varchar(6) not null,
    `column_name` varchar(50) not null,
    `value` varchar(50),
    `type` varchar(50) not null,
    primary key (`project_id`, `resource_code`, `column_name`),
    foreign key (`project_id`) references `projects`(`id`)
) engine=innodb;

create table `resource` (
	`project_id` int not null,
    `resource_code` varchar(6) not null,
    `resource_name` varchar(50) not null,
    primary key (`project_id`, `resource_code`)
) engine=innodb;

create table `formula_page_data` (
	`project_id` int not null,
    `resource_code` varchar(6) not null,
    `column_name` varchar(50) not null,
    `value` varchar(50),
    `type` varchar(50) not null,
    `from_resource` tinyint(1) not null,
    primary key (`project_id`, `resource_code`, `column_name`),
    foreign key (`project_id`) references `projects`(`id`)
) engine=innodb;

create table `formulas` (
	`project_id` int not null,
    `formula_name` varchar(50) not null,
    `formula` varchar(50),
    foreign key (`project_id`) references `projects`(`id`),
    primary key (`project_id`, `formula_name`)
) engine=innodb;

insert into `users` values ('test', 'test', 'testname', curdate());

insert into `projects` (id, name) values (1, 'Project One');

insert into `data` values (1, '000001', 'email', 'e1@test.com', 'text');
insert into `data` values (1, '000001', 'facts', '1233', 'number');
insert into `data` values (1, '000002', 'email', 'e2@test.com', 'text');
insert into `data` values (1, '000002', 'facts', '2244', 'number');

insert into `resource` values (1, '000001', 'res name 1');
insert into `resource` values (1, '000002', 'res name 2');

insert into `formula_page_data` values (1, '000001', 'aa' , '10', 'number', 0);
insert into `formula_page_data` values (1, '000001', 'bb' , '20', 'number', 0);
insert into `formula_page_data` values (1, '000001', 'email' , 'e1@test.com', 'text', 1);
insert into `formula_page_data` values (1, '000001', 'facts' , '123', 'number', 1);
    
    