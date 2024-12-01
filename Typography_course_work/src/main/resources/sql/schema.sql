-- Создание таблицы client
CREATE TABLE IF NOT EXISTS client (
    id bigint PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    second_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    card VARCHAR(50) not null
);

-- Создание таблицы product
CREATE TABLE IF NOT EXISTS product (
    id bigint PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    materials integer[] NOT NULL, -- Для связи с таблицей material через массив id
    price INTEGER NOT NULL
);

-- Создание таблицы material
CREATE TABLE IF NOT EXISTS material (
    id bigint PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    count INTEGER NOT NULL,
    price INTEGER NOT NULL
);

-- Создание таблицы provider
CREATE TABLE IF NOT EXISTS provider (
    id bigint PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    material_id bigint NOT NULL -- Один материал на одного поставщика
--    FOREIGN KEY (material_id) REFERENCES material (id) ON DELETE CASCADE
);

-- Создание таблицы order
CREATE TABLE IF NOT EXISTS "order" (
    id bigint PRIMARY KEY,
    order_items integer[] not null,-- Один клиент в заказе
    client_id bigint
--    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);


-- Создание таблицы order_item
CREATE TABLE IF NOT EXISTS order_item (
    id bigint PRIMARY KEY,
    order_id bigint, -- Один заказ может содержать множество order_item
    product_id bigint NOT NULL, -- Один продукт для одного orderItem
    count INTEGER NOT NULL
--    FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
--    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

---- Добавление связей между product и material через отдельную таблицу
--CREATE TABLE IF NOT EXISTS product_material (
--    product_id serial NOT NULL,
--    material_id serial NOT NULL
----    PRIMARY KEY (product_id, material_id),
----    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
----    FOREIGN KEY (material_id) REFERENCES material (id) ON DELETE CASCADE
--);

