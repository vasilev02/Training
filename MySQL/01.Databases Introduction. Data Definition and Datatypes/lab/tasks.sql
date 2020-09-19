#Task 1 - - -
CREATE TABLE `employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `categories` (
  `id` INT AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`)
  );


#Task 2 - - -
INSERT INTO `employees` (`first_name`, `last_name`) VALUES ('Ivo', 'Kirov');
INSERT INTO `employees` (`first_name`, `last_name`) VALUES ('Pesho', 'Peshov');
INSERT INTO `employees` (`first_name`, `last_name`) VALUES ('k', 'k');


#Task 3 - - -
ALTER TABLE `employees` 
ADD COLUMN `middle_name` VARCHAR(45) NULL AFTER `last_name`;


#Task 4 - - -
ALTER TABLE `products` 
ADD CONSTRAINT fk_products_categories FOREIGN KEY (`category_id`) 
REFERENCES `categories` (`id`);


#Task 5 - - -
ALTER TABLE `employees` 
CHANGE COLUMN `middle_name` `middle_name` VARCHAR(100) NULL DEFAULT NULL ;


