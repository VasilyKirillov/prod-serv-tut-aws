--DROP TABLE IF EXISTS categories;
--DROP TABLE IF EXISTS brands;
--DROP TABLE IF EXISTS products;
--
--CREATE TABLE brands (
--	id INT AUTO_INCREMENT PRIMARY KEY,
--	brand_name VARCHAR (255) NOT NULL
--);
--
--CREATE TABLE categories (
--	id INT AUTO_INCREMENT PRIMARY KEY,
--	category_name VARCHAR (255) NOT NULL
--);
--
--CREATE TABLE products (
--  id INT AUTO_INCREMENT PRIMARY KEY,
--  brand_id INT,
--  category_id INT,
--  title VARCHAR(255) NOT NULL,
--  description VARCHAR(1024) DEFAULT NULL,
--  price DECIMAL (10, 2) NOT NULL,
--  CONSTRAINT fk_products__brand_id FOREIGN KEY (brand_id) REFERENCES brands(id)
--  --CONSTRAINT fk_products__categoty_id FOREIGN KEY (category_id) REFERENCES categories(id)
--);


INSERT INTO categories (id, category_name) VALUES (1, 'Drinks');
INSERT INTO categories (id, category_name) VALUES (2, 'Food');

INSERT INTO brands (id, brand_name) VALUES (1, 'Coca-cola');
INSERT INTO brands (id, brand_name) VALUES (2, 'Nemoloko');
INSERT INTO brands (id, brand_name) VALUES (3, 'Bon Aqua');
INSERT INTO brands (id, brand_name) VALUES (4, 'Crusty loaves');

INSERT INTO products(id, brand_id, category_id, title, description, price) VALUES (1, 1, 1, 'Cola light', 'Phosphoric acid water solution (good for rust removal)', 120);
INSERT INTO products(id, brand_id, category_id, title, description, price) VALUES (2, 2, 1, 'Oat milk', 'Healthy and testy yummy', 82);
INSERT INTO products(id, brand_id, category_id, title, description, price) VALUES (3, 3, 1, 'Cashew milk', 'Healthy and testy yummy', 84);
INSERT INTO products(id, brand_id, category_id, title, description, price) VALUES (4, 4, 2, 'Dark rye bread', 'Everyday meal', 50);
INSERT INTO products(id, brand_id, category_id, title, description, price) VALUES (5, 4, 2, 'Whole grained wheat bread', 'Everyday meal', 55);
