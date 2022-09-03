CREATE TABLE carts
(
    id                      UUID primary key,
    owner_id                bigint references users (id),
    price                   int
);

CREATE TABLE cart_items
(
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