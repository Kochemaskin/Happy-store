create table orders
(
    id                      bigserial primary key,
    user_id                 int,
    foreign key (user_id) references users (id)
);

create table order_items
(
    id                      bigserial primary key,
    order_id                int,
    product_id              int,
    quantity                int,
    price_per_product       int,
    price                   int,
    foreign key (order_id) references orders (id),
    foreign key (product_id) references products (id)
);

insert into orders (user_id)
values
(1),
(1),
(2);

insert into order_items (order_id, product_id, quantity, price_per_product, price)
values
(1, 2, 3, 10, 15);

