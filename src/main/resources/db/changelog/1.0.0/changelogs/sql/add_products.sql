create table if not exists products
(
    id      bigserial primary key,
    number  varchar(255),
    balance numeric,
    type    varchar(40),
    user_id bigint
);

ALTER TABLE products
    ADD CONSTRAINT fk_product_to_user
        FOREIGN KEY (user_id)
            REFERENCES users (id);

