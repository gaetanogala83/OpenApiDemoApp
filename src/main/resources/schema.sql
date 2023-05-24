CREATE TABLE ACCOUNTS(
   id integer not null,
   account_number varchar(255) not null,
   amount decimal not null,
   primary key(id)
);


CREATE TABLE TRANSACTIONS(
   id integer not null auto_increment,
   data date,
   amount decimal,
   account_id integer,
   primary key(id),
   foreign key(account_id) references accounts(id)
);
