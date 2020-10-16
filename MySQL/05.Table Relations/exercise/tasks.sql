#Task 1 - - -
CREATE TABLE `passports`(
`passport_id` INT PRIMARY KEY,
`passport_number` VARCHAR(8) UNIQUE NOT NULL
);

CREATE TABLE `people` (
`person_id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(45) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`passport_id` INT UNIQUE NOT NULL
);

ALTER TABLE `people` ADD CONSTRAINT `fk_people_passports` FOREIGN KEY (`passport_id`) REFERENCES `passports`(`passport_id`);

INSERT INTO `passports` VALUES(101,'N34FG21B'),
(102,'K65LO4R7'),
(103,'ZE657QP2');

INSERT INTO `people` VALUES(1,'Roberto','43300.00',102),
(2,'Tom','56100.00',103),
(3,'Yana','60200.00',101);


#Task 2 - - -
CREATE TABLE `manufacturers`(
`manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`established_on` DATE NOT NULL
);

CREATE TABLE `models`(
`model_id` INT PRIMARY KEY UNIQUE,
`name` VARCHAR(45) UNIQUE NOT NULL,
`manufacturer_id` INT NOT NULL,
CONSTRAINT `fk_models_manuf` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers`(manufacturer_id)
);

INSERT INTO `manufacturers`(`name`,`established_on`) VALUES('BMW','1916-03-01'),
('Tesla','2003-01-01'),
('Lada','1966-05-01');

INSERT INTO `models` VALUES (101,'X1',1),
(102,'i6',1),
(103,'Model S',2),
(104,'Model X',2),
(105,'Model 3',2),
(106,'Nova',3);


#Task 3 - - -
CREATE TABLE `students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45)  NOT NULL
);

CREATE TABLE `exams`(
`exam_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45)  NOT NULL
);

CREATE TABLE `students_exams`(
`student_id` INT,
`exam_id` INT,
CONSTRAINT `pk_students_exams` PRIMARY KEY(`student_id`,`exam_id`),
CONSTRAINT `fk_studentsExam_students` FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`),
CONSTRAINT `fk_studentsExam_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams`(`exam_id`)
);

INSERT INTO students(name,student_id) VALUES('Mila',1),('Toni',2),('Ron',3);
INSERT INTO exams(name,exam_id) VALUES('Spring MVC',101),('Neo4j',102),('Oracle 11g',103);
INSERT INTO students_exams(student_id,exam_id) VALUES(1,101),(1,102),(2,101),(3,103),(2,102),(2,103);


#Task 4 - - -
CREATE TABLE `teachers`(
`teacher_id` INT PRIMARY KEY NOT NULL,
`name` VARCHAR(45)  NOT NULL,
`manager_id` INT 
);

INSERT INTO `teachers` VALUES
(101, 'John', NULL), (105, 'Mark', 101), (106, 'Greta', 101), 
(102, 'Maya', 106), (103, 'Silvia', 106), (104, 'Ted', 105);

ALTER TABLE `teachers` ADD CONSTRAINT `fk_manager_teacher` FOREIGN KEY (`manager_id`) REFERENCES `teachers`(`teacher_id`);


#Task 5 - - -
CREATE TABLE `cities`(
`city_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `customers`(
`customer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`birthday` DATE,
`city_id` INT,
CONSTRAINT `fk_customer_cities` FOREIGN KEY (`city_id`) REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders`(
`order_id` INT PRIMARY KEY AUTO_INCREMENT,
`customer_id` INT,
CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `item_types`(
`item_type_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `items`(
`item_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`item_type_id` INT,
CONSTRAINT `fk_items_itemTypes` FOREIGN KEY (`item_type_id`) REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items`(
`item_id` INT NOT NULL,
`order_id` INT NOT NULL,
CONSTRAINT `pk_itemId_orderId` PRIMARY KEY (`item_id`, `order_id`),
CONSTRAINT `fk_OrderItem_items` FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`),
CONSTRAINT `fk_OrderItem_orders` FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`)
);


#Task 6 - - -
CREATE TABLE `majors`(
`major_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `payments`(
`payment_id` INT PRIMARY KEY AUTO_INCREMENT,
`payment_date` DATE,
`payment_amount` DECIMAL(8,2),
`student_id` INT
);

CREATE TABLE `students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`student_number` VARCHAR(12),
`student_name` VARCHAR(50),
`major_id` INT,
CONSTRAINT `fk_students_majors` FOREIGN KEY (`major_id`) REFERENCES `majors`(`major_id`)
);

ALTER TABLE `payments` ADD CONSTRAINT `fk_payments_students` FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`);

CREATE TABLE `subjects`(
`subject_id` INT PRIMARY KEY AUTO_INCREMENT,
`subject_name` VARCHAR(50)
);

CREATE TABLE `agenda`(
`student_id` INT NOT NULL,
`subject_id` INT NOT NULL,
CONSTRAINT `pk_students_subjects` PRIMARY KEY (`student_id`,`subject_id`),
CONSTRAINT `fk_agenda_students` FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`),
CONSTRAINT `fk_agenda_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects`(`subject_id`)
);


#Task 9 - -  -
SELECT m.mountain_range, p.peak_name, p.elevation AS `peak_elevation`
FROM `peaks` AS p
JOIN `mountains` AS m
ON p.mountain_id = m.id
WHERE m.mountain_range = 'Rila'
ORDER BY `peak_elevation` DESC;