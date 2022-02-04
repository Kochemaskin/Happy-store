create TABLE users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    alias                   varchar(255) not null,
    email                   varchar(50) unique,
);


insert into users (username, password, alias, email)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Bob Johnson', 'bob_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'John Johnson', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);