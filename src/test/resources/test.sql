CREATE TABLE accounts
(name varchar(20) NOT NULL ,
 surname varchar(20) NOT NULL ,
 email varchar(64),
 password varchar(255),
 UNIQUE (name)
);