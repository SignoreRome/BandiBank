create table Users(
    id serial primary key,
    name varchar(100) not null,
    last_name varchar(100) not null,
    account_id int DEFAULT NULL,
    constraint FK_ACC_ID foreign key (account_id) references Accounts(id)
);

create table Accounts(
    id serial primary key,
    balance int DEFAULT 0,
);

insert into Accounts (balance) values (10000), (20000);

insert into Users(name, last_name, account_id) values
('Roman','Volobuev',1), ('Egor','Volobuev',2);
