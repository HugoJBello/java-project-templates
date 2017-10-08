drop database userbase;
create database userbase;

use userbase;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cathegories;
DROP TABLE IF EXISTS cathegories_referenced;

CREATE  TABLE users (
  userid int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (userid));
  
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  userid int(11) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_userid_role (role,userid),
  KEY fk_user_idx (userid),
  CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users (userid));


CREATE TABLE page_entries (
  title text NOT NULL,
  entry_name VARCHAR(500) NOT NULL,
  contents text,
  cathegories text,
  updated_at datetime,
  updated_by int(11),
  created_by int(11),
  primary key (entry_name),
  KEY fk_updated_by_entries (updated_by),
  KEY fk_created_by_entries (created_by),
  CONSTRAINT fk_updated_by_entries FOREIGN KEY (updated_by) REFERENCES users (userid),
  CONSTRAINT fk_created_by_entries FOREIGN KEY (created_by) REFERENCES users (userid));

  CREATE TABLE cathegories (
  title text NOT NULL,
  cathegory_name VARCHAR(500) NOT NULL,
  description text,
  updated_at datetime,
  updated_by int(11),
  created_by int(11),
  primary key (cathegory_name),
  KEY fk_updated_by_cathegories (updated_by),
  KEY fk_created_by_cathegories (created_by),
  CONSTRAINT fk_updated_by_cathegories FOREIGN KEY (updated_by) REFERENCES users (userid),
  CONSTRAINT fk_created_by_cathegories FOREIGN KEY (created_by) REFERENCES users (userid));

CREATE TABLE cathegories_referenced (
  cathegory_name VARCHAR(500) NOT NULL,
  entry_name_referenced VARCHAR(500) NOT NULL,
  referenced_at datetime,
  KEY fk_cathegory_name (cathegory_name),
  KEY fk_entry_name_referenced (entry_name_referenced),
  CONSTRAINT fk_cathegory_name FOREIGN KEY (cathegory_name) REFERENCES cathegories (cathegory_name),
  CONSTRAINT fk_entry_name_referenced FOREIGN KEY (entry_name_referenced) REFERENCES page_entries (entry_name));



INSERT INTO users(username,email,password,enabled)
VALUES ('hjbello','abc@abc.com','1234', true);



INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_ADMIN');

INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 1', 'entry_test_1', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);


INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 2', 'entry_test_2', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 3', 'entry_test_3', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 4', 'entry_test_4', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 5', 'entry_test_5', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 6', 'entry_test_6', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 7', 'entry_test_7', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory test; cathegory 2',001,001);


INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 8', 'entry_test_8', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 9', 'entry_test_9', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 10', 'entry_test_10', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 11', 'entry_test_11', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 12', 'entry_test_12', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);
INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 13', 'entry_test_13', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory test; cathegory 2',001,001);


INSERT INTO cathegories(title, cathegory_name, created_by,updated_by)
values ('cathegory test','cathegory_test', 001,001);

INSERT INTO cathegories(title, cathegory_name, created_by,updated_by)
values ('cathegory 1','cathegory_1', 001,001);

insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_1');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_2');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_3');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_4');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_5');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_6');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_7');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_8');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_9');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_10');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_11');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_12');
insert into cathegories_referenced(cathegory_name,entry_name_referenced)
values ('cathegory_1', 'entry_test_13');


select * from users;

select * from cathegories;

select * from cathegories_referenced;
