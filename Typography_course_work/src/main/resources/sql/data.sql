-- Заполнение таблицы client (10 клиентов)
INSERT INTO "public".client (id, "name", second_name, email, phone, card)
VALUES
(1, 'Иван', 'Иванов', 'ivanov@example.com', '+79161234567', '1234567890'),
(2, 'Мария', 'Петрова', 'petrova@example.com', '+79162345678', '2345678901'),
(3, 'Алексей', 'Смирнов', 'smirnov@example.com', '+79163456789', '3456789012'),
(4, 'Ольга', 'Кузнецова', 'kuznetsova@example.com', '+79164567890', '4567890123'),
(5, 'Дмитрий', 'Михайлов', 'mikhaylov@example.com', '+79165678901', '5678901234'),
(6, 'Елена', 'Федорова', 'fedorova@example.com', '+79166789012', '6789012345'),
(7, 'Сергей', 'Попов', 'popov@example.com', '+79167890123', '7890123456'),
(8, 'Анастасия', 'Кузина', 'kuzina@example.com', '+79168901234', '8901234567'),
(9, 'Константин', 'Николаев', 'nikolaev@example.com', '+79169012345', '9012345678'),
(10, 'Татьяна', 'Григорьева', 'grigorieva@example.com', '+79170123456', '0123456789')
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы material (14 материалов)
INSERT INTO "public".material (id, title, count, count_of_spent, price)
VALUES
(1, 'Бумага А4', 1000, 200, 15),
(2, 'Бумага А3', 800, 150, 25),
(3, 'Картон', 500, 100, 30),
(4, 'Клеевая лента', 200, 50, 10),
(5, 'Термоклей', 100, 20, 40),
(6, 'Тетрадный переплет', 300, 70, 35),
(7, 'Ламинат', 600, 150, 45),
(8, 'Переплетный материал', 150, 30, 20),
(9, 'Грета', 400, 90, 18),
(10, 'Гильотина', 50, 5, 200),
(11, 'Фольга для печати', 200, 40, 50),
(12, 'Цветной картон', 600, 120, 60),
(13, 'Клеящийся материал', 300, 50, 22),
(14, 'Шелкограф', 100, 10, 70)
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы provider (14 поставщиков)
INSERT INTO "public".provider (id, "name", material_id, contact_info)
VALUES
(1, 'Поставщик №1', 1, 'contact1@example.com'),
(2, 'Поставщик №2', 2, 'contact2@example.com'),
(3, 'Поставщик №3', 3, 'contact3@example.com'),
(4, 'Поставщик №4', 4, 'contact4@example.com'),
(5, 'Поставщик №5', 5, 'contact5@example.com'),
(6, 'Поставщик №6', 6, 'contact6@example.com'),
(7, 'Поставщик №7', 7, 'contact7@example.com'),
(8, 'Поставщик №8', 8, 'contact8@example.com'),
(9, 'Поставщик №9', 9, 'contact9@example.com'),
(10, 'Поставщик №10', 10, 'contact10@example.com'),
(11, 'Поставщик №11', 11, 'contact11@example.com'),
(12, 'Поставщик №12', 12, 'contact12@example.com'),
(13, 'Поставщик №13', 13, 'contact13@example.com'),
(14, 'Поставщик №14', 14, 'contact14@example.com')
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы product (10 продуктов)
INSERT INTO "public".product (id, title, materials, price)
VALUES
(1, 'Книга', ARRAY[1, 2, 3], 500),
(2, 'Брошюра', ARRAY[4, 6], 300),
(3, 'Плакат', ARRAY[7, 8], 150),
(4, 'Визитки', ARRAY[9, 10], 100),
(5, 'Листовки', ARRAY[11, 12], 200),
(6, 'Календарь', ARRAY[13, 14], 350),
(7, 'Печать на футболках', ARRAY[1, 3, 7], 450),
(8, 'Пакеты', ARRAY[4, 6, 9], 250),
(9, 'Рекламные буклеты', ARRAY[2, 12], 400),
(10, 'Пластиковые карты', ARRAY[10, 5], 600)
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы "order" (5 заказов)
INSERT INTO "public"."order" (id, order_items, client_id)
VALUES
(1, ARRAY[1, 2], 1),
(2, ARRAY[3, 4], 2),
(3, ARRAY[5, 6], 3),
(4, ARRAY[7, 8], 4),
(5, ARRAY[9, 10], 5)
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы order_item (10 позиций заказа)
INSERT INTO "public".order_item (id, order_id, product_id, count)
VALUES
(1, 1, 1, 3),
(2, 1, 2, 5),
(3, 2, 3, 2),
(4, 2, 4, 7),
(5, 3, 5, 1),
(6, 3, 6, 8),
(7, 4, 7, 4),
(8, 4, 8, 6),
(9, 5, 9, 2),
(10, 5, 10, 10)
ON CONFLICT (id) DO NOTHING;

-- Заполнение таблицы materials_turnover (движение материалов)
INSERT INTO "public".materials_turnover (id, material_id, count)
VALUES
(1, 1, 100),
(2, 2, 50),
(3, 3, 30),
(4, 4, 20),
(5, 5, 10),
(6, 6, 40),
(7, 7, 60),
(8, 8, 80),
(9, 9, 70),
(10, 10, 15),
(11, 11, 25),
(12, 12, 100),
(13, 13, 50),
(14, 14, 10)
ON CONFLICT (id) DO NOTHING;
