create TABLE products (
    id serial NOT NULL primary key,
    name varchar NULL,
    price float8 NULL
);

insert into products (name,price) values
        ('bread',100),
        ('cheese',320),
        ('milk',65);