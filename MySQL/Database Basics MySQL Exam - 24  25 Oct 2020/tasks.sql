#EXAM - - - - - - - - -
# ExamTime

#Task 1 - - -
CREATE DATABASE `softuni_stores_system`;

CREATE TABLE `towns`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL UNIQUE,
`town_id` INT NOT NULL,
CONSTRAINT `fk_addresses_towns` FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE `stores`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE,
`rating` FLOAT NOT NULL,
`has_parking` TINYINT(1) DEFAULT FALSE,
`address_id` INT NOT NULL,
CONSTRAINT `fk_stores_addresses` FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(15) NOT NULL,
`middle_name` CHAR(1),
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(19,2) NOT NULL DEFAULT 0,
`hire_date` DATE NOT NULL,
`manager_id` INT,
`store_id` INT NOT NULL,
CONSTRAINT `fk_employees_employeees` FOREIGN KEY (`manager_id`) REFERENCES `employees`(`id`),
CONSTRAINT `fk_employees_stores` FOREIGN KEY (`store_id`) REFERENCES `stores`(`id`)
);

CREATE TABLE `pictures`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`url` VARCHAR(100) NOT NULL,
`added_on` DATETIME NOT NULL
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE `products`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE,
`best_before` DATE,
`price` DECIMAL (10,2) NOT NULL,
`description` TEXT,
`category_id` INT NOT NULL,
`picture_id` INT NOT NULL,
CONSTRAINT `fk_products_categories` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`),
CONSTRAINT `fk_products_pictures` FOREIGN KEY (`picture_id`) REFERENCES `pictures`(`id`)
);

CREATE TABLE `products_stores`(
`product_id` INT NOT NULL,
`store_id` INT NOT NULL,
CONSTRAINT `pk_products_stores` PRIMARY KEY (`product_id`,`store_id`),
CONSTRAINT `fk_productsStores_products` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
CONSTRAINT `fk_productsStores_stores` FOREIGN KEY (`store_id`) REFERENCES `stores`(`id`)
);


#Test 1 - - -
SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'softuni_stores_system'
ORDER BY COLUMN_NAME, TABLE_NAME;

#Test 2 - - -
SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'softuni_stores_system'
ORDER BY COLUMN_NAME, TABLE_NAME;


#Test 3 - - -
SELECT COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'softuni_stores_system'
  AND COLUMN_NAME IN ('id','product_id','store_id',
                      'town_id', 'address_id', 'category_id', 'picture_id','manager_id')
ORDER BY COLUMN_NAME, TABLE_NAME DESC, COLUMN_KEY DESC;

#Task 2 - - -
INSERT INTO products_stores
SELECT id, 1 FROM products WHERE id NOT IN (SELECT DISTINCT product_id FROM products_stores);

#Test Query
SELECT store_id, s.name, p.name, product_id FROM products_stores
JOIN products p ON p.id = products_stores.product_id
JOIN stores s ON products_stores.store_id = s.id
ORDER BY product_id, store_id;


#Task 3 - - -
UPDATE employees 
SET manager_id = 3, salary = salary - 500
WHERE YEAR(hire_date) > 2003 AND store_id NOT IN (5, 14);

#Test Query
SELECT first_name, salary, hire_date, id 
FROM employees 
WHERE manager_id = 3;

#Task 4 - - -
DELETE FROM employees
WHERE manager_id IS NOT NULL AND salary >= 6000;

#Test Query
SELECT first_name, salary, hire_date, id
FROM employees;

#Task 5 - - -
SELECT `first_name`, `middle_name`, `last_name`, `salary`, `hire_date`
FROM `employees`
ORDER BY `hire_date` DESC;


#Task 6 - - -
SELECT p.`name`, p.`price`, p.`best_before`, CONCAT(LEFT(p.`description`, 10), '...') AS `short_description`, pic.`url`
FROM `products` AS p
JOIN `pictures` AS pic
ON p.picture_id = pic.id
WHERE CHAR_LENGTH(p.description) > 100 AND YEAR(pic.added_on) < 2019 AND p.price > 20
ORDER BY p.`price` DESC;


#Task 7 - - -
SELECT s.`name`, COUNT(p.id) AS `product_count`, ROUND(AVG(p.`price`), 2) AS `avg`
FROM `stores` AS s
LEFT JOIN `products_stores` AS ps
ON s.id = ps.store_id
LEFT JOIN `products` AS p
ON ps.product_id = p.id
GROUP BY s.`name`
ORDER BY `product_count` DESC, `avg` DESC, s.`id`;


#Task 8 - - -
SELECT CONCAT(e.first_name,' ', e.`last_name`) AS `Full_name`, s.`name` AS `Store_name`, a.`name`, e.`salary`
FROM `employees` AS e
JOIN `stores` AS s
ON e.store_id = s.id
JOIN `addresses` AS a
ON s.address_id = a.id
WHERE e.`salary` < 7000 AND a.`name` LIKE '%a%' AND CHAR_LENGTH(s.`name`) > 5
ORDER BY e.`id`;


#Task 9 - - -
SELECT REVERSE(s.`name`) AS `reversed_name`, CONCAT(UPPER(t.`name`),'-',a.`name`) AS `full_address`, COUNT(DISTINCT e.id) AS `employees_count`,
MIN(p.price) AS `min_price`, COUNT(DISTINCT p.`id`) AS `products_count`, DATE_FORMAT(MAX(pic.`added_on`),'%D-%b-%Y') AS `newest_pic`
FROM `stores` AS s
JOIN `addresses` AS a
ON s.address_id = a.id
JOIN `towns` AS t
ON a.town_id = t.id
LEFT JOIN `employees` AS e
ON s.id = e.store_id
JOIN `products_stores` AS ps
ON s.id = ps.store_id
JOIN `products` AS p
ON ps.product_id = p.id
JOIN `pictures` AS pic
ON p.picture_id = pic.id
GROUP BY s.`name`
HAVING `min_price` > 10
ORDER BY `reversed_name` ASC, `min_price` ASC;


#Task 10 - - -
DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS TEXT
DETERMINISTIC
BEGIN

	DECLARE result TEXT;
    
    SET result := (
    
    SELECT CONCAT(e.`first_name`,' ',e.`middle_name`,'. ',e.`last_name`,' works in store for ',TIMESTAMPDIFF(year,e.`hire_date`, '2020-10-18'), ' years') AS `full_info`
    FROM `stores` AS s
    JOIN `employees` AS e
    ON s.id = e.store_id
    WHERE s.`name` = store_name
    ORDER BY e.`salary` DESC
    LIMIT 1
    
    );

	RETURN result;

END $$

SELECT udf_top_paid_employee_by_store('Stronghold') as 'full_info';

SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';


#Task 11 - - -
DELIMITER $$
CREATE PROCEDURE udp_update_product_price(address_name VARCHAR(50))
BEGIN

	UPDATE `products` AS p
    JOIN `products_stores` AS ps
    ON p.id = ps.product_id
    JOIN `stores` AS s
    ON ps.store_id = s.id
    JOIN `addresses` AS a
    ON s.address_id = a.id
	SET p.`price` = (

	 CASE
		WHEN LEFT(a.`name`, 1) LIKE '0' THEN p.`price` + 100
		WHEN LEFT(a.`name`, 1) NOT LIKE '0' THEN p.`price` + 200
	END
    
	)
    WHERE a.`name` = address_name;

END $$

CALL udp_update_product_price('07 Armistice Parkway');
SELECT name, price FROM products WHERE id = 15;

CALL udp_update_product_price('1 Cody Pass');
SELECT name, price FROM products WHERE id = 17;

