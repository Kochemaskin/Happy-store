create table products (
    id              bigserial primary key,
    title           VARCHAR(255),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

insert into products (title, price) values
        ('bread',100),
        ('cheese',320),
        ('milk',65),
        ('chocolate', 50),
        ('salt', 17),
        ('chicken', 100),
        ('potato', 7),
        ('cucumber', 4);

create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    alias                   varchar(255) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
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

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, alias, email)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Bob Johnson', 'bob_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'John Johnson', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

create table orders (
    id                      bigserial primary key,
    owner_user_id           bigint references users (id),
    price                   bigint,
    delivery_address        varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table order_items (
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    title                   varchar(255),
    quantity                int,
    price_per_product       int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table carts (
    id                      UUID primary key,
    price                   int
);

create table cart_items (
    id                bigserial primary key,
    cart_id           UUID references carts (id),
    product_id        bigint references products (id),
    title             varchar(255),
    quantity          int,
    price_per_product int,
    price             int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);