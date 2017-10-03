create database userbase;

use userbase;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
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
  KEY fk_updated_by (updated_by),
  KEY fk_created_by (created_by),
  CONSTRAINT fk_updated_by FOREIGN KEY (updated_by) REFERENCES users (userid),
  CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES users (userid));


INSERT INTO users(username,email,password,enabled)
VALUES ('hjbello','abc@abc.com','1234', true);

select * from users;

INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES (001, 'ROLE_ADMIN');
