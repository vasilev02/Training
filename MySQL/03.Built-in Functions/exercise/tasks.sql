#Task 1 - - -
SELECT `first_name`,`last_name` FROM `employees`
WHERE `first_name` LIKE 'Sa%'
ORDER BY `employee_id` ASC;


#Task 2 - - -
SELECT `first_name`,`last_name` FROM `employees`
WHERE `last_name` LIKE '%ei%'
ORDER BY `employee_id` ASC;


#Task 3 - - -
SELECT `first_name` FROM `employees`
WHERE `department_id` IN(3,10) AND YEAR(`hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id` ASC;


#Task 4 - - -
SELECT `first_name`,`last_name`FROM `employees`
WHERE `job_title` NOT LIKE '%engineer%'
ORDER BY `employee_id` ASC;


#Task 5 - - -
SELECT `name` FROM `towns`
WHERE LENGTH(`name`) IN(5,6)
ORDER BY `name` ASC;


#Task 6 - - -
SELECT `town_id`,`name` FROM `towns`
WHERE `name` LIKE 'M%' OR `name` LIKE 'K%' OR `name` LIKE 'B%' OR `name` LIKE 'E%'
ORDER BY `name` ASC;


#Task 7 - - -
SELECT `town_id`,`name` FROM `towns`
WHERE `name` NOT LIKE 'R%' AND `name` NOT LIKE 'B%' AND `name` NOT LIKE 'D%'
ORDER BY `name` ASC;


#Task 8 - - -
CREATE VIEW `v_employees_hired_after_2000` AS
SELECT `first_name`,`last_name` FROM `employees`
WHERE YEAR(`hire_date`) > 2000;

SELECT * FROM `v_employees_hired_after_2000`;


#Task 9 - - -
SELECT `first_name`,`last_name` FROM `employees`
WHERE LENGTH(`last_name`) = 5;


#Task 10 - - -
SELECT `country_name`,`iso_code` FROM `countries`
WHERE `country_name` LIKE '%A%A%A%'
ORDER BY `iso_code` ASC;


#Task 11 - - -
SELECT `peak_name`,`river_name`, LOWER(CONCAT(`peak_name`,SUBSTRING(`river_name`,2))) AS 'mix'
FROM `peaks`,`rivers`
WHERE RIGHT(`peak_name`,1) = LEFT(`river_name`,1)
ORDER BY `mix` ASC;


#Task 12 - - -
SELECT `name`,DATE_FORMAT(`start`,'%Y-%m-%d') FROM `games`
WHERE YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY `start` ASC , name ASC
LIMIT 50;


#Task 13 - - -
SELECT `user_name` , SUBSTRING_INDEX(`email`, '@', -1) AS `Email Provider` 
FROM `users`
ORDER BY `Email Provider` ASC , `user_name` ASC;


#Task 14 - - -
SELECT `user_name`, `ip_address` FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name` ASC;


#Task 15 - - -
SELECT `name` ,
CASE
WHEN HOUR(`start`) >=0 AND HOUR(`start`) <12 THEN 'Morning'
WHEN HOUR(`start`) >=12 AND HOUR(`start`) <18 THEN 'Afternoon'
WHEN HOUR(`start`) >=18 AND HOUR(`start`) <24 THEN 'Evening'
END AS `Part of the Day` ,

CASE
WHEN `duration` <=3 THEN 'Extra Short'
WHEN `duration` >3 AND `duration` <=6  THEN 'Short'
WHEN `duration` >6 AND `duration` <=10  THEN 'Long'
ELSE 'Extra Long'
END AS 'Duration'
FROM `games`;


#Task 16 - - -
SELECT `product_name` , `order_date` , 
DATE_ADD( `order_date`, INTERVAL 3 DAY) AS 'pay_due' ,
DATE_ADD( `order_date`, INTERVAL 1 MONTH) AS 'deliver_due'
FROM `orders`;