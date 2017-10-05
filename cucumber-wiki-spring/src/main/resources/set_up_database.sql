drop database userbase;
create database userbase;

use userbase;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cathegories;


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

INSERT INTO users(username,email,password,enabled)
VALUES ('hjbello','abc@abc.com','1234', true);

select * from users;

INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_ADMIN');

INSERT INTO page_entries( title, entry_name, contents,cathegories,created_by,updated_by)
values ('entry test 1', 'entry_test_1', '## Title \n **bold** this is entry test 1\n *italic*', 'cathegory 1; cathegory 2',001,001);

