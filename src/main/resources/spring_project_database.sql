Table: 
projects, resource, data


create table project(
project_id int not null auto_increment,
project_name varchar(50) not null,
Primary key(project_id)
);

create table resource(
project_id int,
resource_code int not null,
resource_name varchar(100),
primary key(resource_code),
constraint fk_project_res foreign key (project_id)
references project(project_id)
);

create table data(
	data_id timestamp default current_timestamp,
	project_id int,
	resource_code int,
	column_name varchar(50),
	value varchar(50),
	type varchar(50),
	primary key(data_id),
	constraint fk_project_data foreign key (project_id)
	references project (project_id)
	on delete cascade,
	constraint fk_res_data foreign key (resource_code)
	references resource(resource_code)
	on delete cascade
);

create table formula_page_data(
	fpage_id int not null auto_increment,
	project_id int,
	resource_code int,
	column_name varchar(50) not null,
	from_resource BOOLEAN,
	value varchar(50),
	type varchar(50) not null,
	primary key(fpage_id),
	constraint fk_project_fpage foreign key (project_id)
	references project(project_id)
	on delete cascade,
	constraint fk_res_fpage foreign key (resource_code)
	references resource(resource_code)
	on delete cascade
);


create table formula_name(
formula_id int not null auto_increment,
project_id int,
formula_name varchar(50),
formula varchar(255),
primary key(formula_id),
constraint fk_project_fname foreign key (project_id)
references project(project_id)
on delete cascade
);


 alter table formula_page_data
 add from_resource BOOLEAN;
