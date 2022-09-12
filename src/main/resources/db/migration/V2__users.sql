CREATE TABLE users
(
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    alias                   varchar(255) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE roles
(
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

INSERT INTO roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, password, alias, email)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Bob Johnson', 'bob_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'John Johnson', 'john_johnson@gmail.com');

INSERT INTO users_roles (user_id, role_id)
values
(1, 1),
(2, 2);