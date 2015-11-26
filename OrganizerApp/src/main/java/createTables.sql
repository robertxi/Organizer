
create table task (
  id BIGINT NOT NULL AUTO_INCREMENT,

	user_id INT not null, /*LONG,FLAT*/
	title VARCHAR(30) not null,
	description VARCHAR(200) not null,
	date_created VARCHAR(30) not null,
	date_modified VARCHAR(30) not null,
	primary key(id)
);

create table task_item(
	id BIGINT NOT NULL AUTO_INCREMENT,
    task_id BIGINT NOT NULL,
    title VARCHAR(300) not null,
	date_created VARCHAR(30) not null,
	date_modified VARCHAR(30) not null,
    description VARCHAR(30) not null,
    FOREIGN KEY (task_id) REFERENCES task(id),
    primary key(id)
);

create table comments(
	id BIGINT NOT NULL AUTO_INCREMENT,
    taskitem_id BIGINT NOT NULL,
    title VARCHAR(300) not null,
    date_created varchar(30) not null,
    FOREIGN KEY (taskitem_id) REFERENCES task_item(id),
    primary key(id)
);

create table users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    fName varchar(50),
    lName VARCHAR(50),
    email VARCHAR(50),
    primary key(id)
);

Alter table comments change content description varchar(300);
Alter table task_item change content title varchar(300);
Alter table task_item change status description varchar(30);
ALTER TABLE task_item ADD user_id int;

alter table task modify column date_created varchar(30);
alter table task modify column date_modified varchar(30);
update task set date_created = null;
update task set date_modified = null;
alter table task modify column date_created bigint;
alter table task modify column date_modified bigint;

alter table task_item modify column date_created varchar(30);
alter table task_item modify column date_modified varchar(30);
update task_item set date_created = null;
update task_item set date_modified = null;
alter table task_item modify column date_created bigint;
alter table task_item modify column date_modified bigint;

alter table comments modify column date_created varchar(30);
update comments set date_created = null;
alter table comments modify column date_created bigint;