
create database dbhugoj;

use dbhugoj;
drop table users;

-- create table users(
-- name varchar(30),
-- surname varchar(30),
-- email varchar(30),
-- id varchar(20),
-- primary key(id)
-- );

-- ALTER TABLE users MODIFY COLUMN id INT auto_increment;

-- ALTER TABLE users
-- ADD username varchar(30);

GRANT ALL PRIVILEGES ON *.* TO '%'@'1.2.3.4' WITH GRANT OPTION;

-- drop table users;

create table users(
username varchar(30),
password varchar(30),
name varchar(30),
surname varchar(30),
email varchar(30),
id varchar(20),
primary key(username, password)
);

ALTER TABLE users MODIFY COLUMN id INT auto_increment;
