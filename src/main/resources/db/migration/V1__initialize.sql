CREATE TABLE products (
    id              bigserial primary key,
    title           VARCHAR(255),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

INSERT INTO products (title,price) values
        ('bread',100),
        ('cheese',320),
        ('milk',65),
        ('Chocolate', 50),
        ('Salt', 17),
        ('Chicken', 100),
        ('Potato', 7),
        ('Cucumber', 4);