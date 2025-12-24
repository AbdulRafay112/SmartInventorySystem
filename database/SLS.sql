CREATE DATABASE IF NOT EXISTS inventorydb;
USE inventorydb;

CREATE TABLE FOOD (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(50) NOt null,
    unit INT,
    expiry_date DATE,
    added_date DATE,
    price INT NOT NULL
);
Select * from Food;
ALTER TABLE Food
ADD price INT NOT NULL;
DROp TABLE FOOD