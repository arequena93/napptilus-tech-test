DROP TABLE IF EXISTS price;
CREATE TABLE price (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id VARCHAR(50) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price FLOAT NOT NULL,
    currency VARCHAR(5) NOT NULL
);