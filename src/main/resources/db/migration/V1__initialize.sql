CREATE TABLE products (
    id              bigserial primary key,
    title           VARCHAR(255),
    price           int,
    created_at      timestamp default current_timestamp,
    modified_at     timestamp default current_timestamp
);

INSERT INTO products (title,price) VALUES
        ('bread',100),
        ('cheese',320),
        ('milk',65);