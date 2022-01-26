create table users(
    id serial primary key,
    first_name varchar(50) unique not null,
    last_name varchar(50) not null,
    age integer,
    email varchar(100) not null,
    password varchar(20)
);

truncate table users;

insert into users(id, first_name, last_name, age, email, password)
values (1, 'James', 'Stewart', 33, 'JamesStewart@gmail.com', '123'),
       (2, 'Deborah', 'Kerr', 45, 'FordKerr@gmail.com', '123'),
       (3, 'Peter', 'OToole', 28, 'PeterOToole@gmail.com', '123'),
       (4, 'Robert', 'De_Niro', 61, 'Robert_De_Niro@gmail.com', '123'),
       (5, 'Murray', 'Abraham', 54, 'Abraham@gmail.com', '123'),
       (6, 'Harrison', 'Ford', 38, 'Harrison_Ford@gmail.com', '123');

/*-----------------------------------*/

create table roles(
    id serial primary key,
    role_name varchar(50) not null
);

truncate table roles;

insert into roles(id, role_name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

/*-----------------------------------*/

create table users_roles(
    user_id integer,
    role_id integer,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

truncate users_roles;

insert into users_roles(user_id, role_id)
VALUES (1, 1),
       (1, 2);