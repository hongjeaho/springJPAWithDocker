create table user
(
    seq    bigint auto_increment primary key,
    name   varchar(255) not null,
    age    int not null,
    email  varchar(255)  not null,
    passwd varchar(255) not null
);
