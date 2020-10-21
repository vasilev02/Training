#Task 1 - - -
DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(45))
RETURNS INT
DETERMINISTIC
BEGIN
       DECLARE employees_count INT;
       SET employees_count := (SELECT COUNT(*) 
FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
JOIN `towns` AS t
ON a.`town_id` = t.`town_id`
WHERE t.`name` = town_name);

RETURN employees_count;
END $$

SELECT ufn_count_employees_by_town('Sofia') AS 'count';


#Task 2 - - -
DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(45))
BEGIN

	UPDATE `employees` AS e
    JOIN `departments` AS d
    ON e.`department_id` = d.`department_id`
    SET `salary` = `salary` * 1.05
    WHERE d.`name` = department_name;
    
END $$

CALL usp_raise_salaries('Finance');


#Task 3 - - -
DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN

	START TRANSACTION;
    IF ((SELECT COUNT(*) FROM `employees` AS e WHERE e.employee_id = id) != 1) THEN ROLLBACK;

	ELSE
    
	UPDATE `employees` AS e
    SET `salary` = `salary`* 1.05
    WHERE e.`employee_id` = id;
    
    END IF;

END $$

CALL usp_raise_salary_by_id(178);


#Task 4 - - -

CREATE TABLE `deleted_employees`(
`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20),
`last_name` VARCHAR(20),
`middle_name` VARCHAR(20),
`job_title` VARCHAR(20),
`department_id` INT,
`salary` DOUBLE
);

SET FOREIGN_KEY_CHECKS=0;
SET FOREIGN_KEY_CHECKS=1;

DELIMITER $$
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN

	INSERT INTO deleted_employees (first_name,last_name,middle_name,job_title,department_id,salary)
	VALUES(OLD.first_name,OLD.last_name,OLD.middle_name,OLD.job_title,OLD.department_id,OLD.salary);

END $$

DELETE FROM `employees` AS e
WHERE e.`employee_id` = 1;