-- Создание таблицы client
CREATE TABLE IF NOT EXISTS client (
    id bigint PRIMARY KEY,
    "name" VARCHAR(100) ,
    second_name VARCHAR(100) ,
    email VARCHAR(255) ,
    phone VARCHAR(15) ,
    card VARCHAR(50)
);

-- Создание таблицы product
CREATE TABLE IF NOT EXISTS product (
    id bigint PRIMARY KEY,
    title VARCHAR(255) ,
    materials integer[] , -- Для связи с таблицей material через массив id
    price INTEGER
);

-- Создание таблицы material
CREATE TABLE IF NOT EXISTS material (
    id bigint PRIMARY KEY,
    title VARCHAR(255),
    count INTEGER,
    count_of_spent integer,
    price INTEGER
);


create table if not exists provider(
    id bigint primary key,
    "name" varchar(255),
    material_id bigint,
    contact_info varchar(255)
);


CREATE TABLE IF NOT EXISTS "order" (
    id bigint PRIMARY KEY,
    order_items integer[],
    client_id bigint
);



CREATE TABLE IF NOT EXISTS order_item (
    id bigint PRIMARY KEY,
    order_id bigint,
    product_id bigint,
    count INTEGER
);

create table if not exists materials_turnover(
    id bigint primary key,
    material_id bigint,
    count integer
);