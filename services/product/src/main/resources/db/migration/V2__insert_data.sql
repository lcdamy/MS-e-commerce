-- Insert categories
INSERT INTO category (id, name, description)
VALUES
    (nextval('category_seq'), 'Electronics', 'Phones, laptops, and accessories'),
    (nextval('category_seq'), 'Books', 'Printed and digital books'),
    (nextval('category_seq'), 'Groceries', 'Everyday consumables'),
    (nextval('category_seq'), 'Clothing', 'Apparel and fashion items');

-- If you want to know the IDs assigned, you can SELECT them:
-- SELECT * FROM category;

-- For clarity, let’s assume the sequence started at 1 and incremented by 50:
-- category IDs would be 1, 51, 101, 151
-- We'll use those in product inserts below.
-- If your sequence is at a different value, just adjust the category_id in products.

-- Insert products
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'iPhone 15', 'Latest Apple smartphone', 25, 1299.00, 1),
    (nextval('product_seq'), 'MacBook Air', '13-inch lightweight laptop', 10, 1699.00, 1),
    (nextval('product_seq'), 'Spring in Action', 'Java Spring framework book', 40, 59.99, 51),
    (nextval('product_seq'), 'Whole Wheat Bread', 'Freshly baked daily', 100, 3.50, 101),
    (nextval('product_seq'), 'T-Shirt', 'Cotton unisex t-shirt', 75, 15.00, 151);
