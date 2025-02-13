
CREATE TABLE IF NOT EXISTS Orders (
    id SERIAL PRIMARY KEY,
    order_date TIMESTAMP,
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Product (
    id VARCHAR(255) PRIMARY KEY,
    product_name VARCHAR(255),
    price DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id VARCHAR(255),
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
     UNIQUE (order_id, product_id)
);

INSERT INTO product (id,product_name, price) VALUES ('Product101','Product A', 300.00);
INSERT INTO product (id,product_name, price) VALUES ('Product102','Product B', 200.00);



INSERT INTO orders (id,order_date, status)  VALUES (101,'2023-06-30 10:00:00', 'COMPLETED');
INSERT INTO orders (id,order_date, status)  VALUES (102,'2023-06-30 12:00:00', 'COMPLETED');


INSERT INTO item (order_id, product_id, quantity) VALUES (101, 'Product101', 1), (101, 'Product102', 2);
INSERT INTO item (order_id, product_id, quantity) VALUES (102, 'Product102', 2);

