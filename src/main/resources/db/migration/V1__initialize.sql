CREATE TABLE products (
    id serial NOT NULL primary key,
    name varchar NULL,
    price float8 NULL
);

INSERT INTO products (name,price) VALUES
        ('bread',100),
        ('cheese',320),
        ('milk',65);