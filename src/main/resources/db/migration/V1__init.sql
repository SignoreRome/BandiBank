create sequence CreditCard_seq
    start with 5000
    increment by 10
    maxvalue 10000;

create sequence DebitCard_seq
    start with 0
    increment by 10
    minvalue 0
    maxvalue 4999;

create table Accounts(
    id serial primary key,
    balance int DEFAULT 0
);

create table CreditCards(
    number int primary key default nextval('CreditCard_seq'),
    valid_thru date not null default current_date+365*3,
    balance int not null default 0,
    account_id int not null,
    constraint FK_ACC_ID foreign key (account_id) references Accounts(id)
);

create table DebitCards(
    number int primary key default  nextval('DebitCard_seq'),
    valid_thru date not null default current_date+365*5,
    balance int not null default 0,
    account_id int not null,
    constraint FK_ACC_ID foreign key (account_id) references Accounts(id)
);

create table Users(
    id serial primary key,
    name varchar(100) not null,
    last_name varchar(100) not null,
    account_id int DEFAULT NULL,
    email varchar(100) default null,
    phone varchar not null unique,
    constraint FK_ACC_ID foreign key (account_id) references Accounts(id),
    constraint CH_USER_PHONE check ( length(phone) = 7 )
);

insert into Accounts (balance) values (Default),(DEFAULT);

insert into CreditCards (balance, account_id) values (10000, 1),(1000, 2);

insert into DebitCards (balance, account_id) values (3000, 1),(4000, 2);

insert into Users(name, last_name, account_id, phone) values
('Roman','Volobuev',1, '3506538'), ('Egor','Volobuev',2,'3506539');