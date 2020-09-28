#Task 1 - - -
SELECT * FROM `departments` ORDER BY `department_id` ASC;


#Task 2 - - -
SELECT `name` FROM `departments` ORDER BY `department_id` ASC;


#Task 3 - - -
SELECT `first_name` , `last_name` , `salary`  FROM `employees` ORDER BY `employee_id`;

#Task 4 - - -
SELECT `first_name` , `middle_name` , `last_name` FROM `employees` ORDER BY `employee_id`;


#Task 5 - - -
SELECT concat(`first_name`, '.' , `last_name` , '@softuni.bg') AS 'full_email_address' FROM `employees`;


#Task 6 - - -
SELECT DISTINCT `salary` FROM `employees` ORDER BY `employee_id`;


#Task 7 - - -
SELECT * FROM `employees` WHERE `job_title` = 'Sales Representative';


#Task 8 - - -
SELECT `first_name` , `last_name` , `job_title` FROM `employees` 
WHERE `salary` BETWEEN 20000 AND 30000 
ORDER BY `employee_id` ASC;


#Task 9 - - -
SELECT concat_ws(' ',`first_name`, `middle_name` ,`last_name`) AS 'Full Name' 
FROM `employees` 
WHERE `salary` IN (25000,14000,12500,23600);


#Task 10 - - -
SELECT `first_name` , `last_name` FROM `employees`
WHERE `manager_id` IS NULL;


#Task 11 - - -
SELECT `first_name` , `last_name` , `salary` FROM `employees`
WHERE `salary` > 50000
ORDER BY `salary` DESC;


#Task 12 - - -
SELECT `first_name` , `last_name` FROM `employees`
ORDER BY `salary` DESC
LIMIT 5;


#Task 13 - - -
SELECT `first_name` , `last_name` FROM `employees`
WHERE `department_id` != 4;


#Task 14 - - -
SELECT * FROM `employees`
ORDER BY `salary` DESC, `first_name` ASC, `last_name` DESC, `middle_name` ASC , `employee_id` ASC;


#Task 15 - - -
CREATE VIEW `v_employees_salaries` AS
SELECT `first_name` , `last_name` , `salary` FROM `employees`;
SELECT * FROM `v_employees_salaries`;


#Task 16 - - -
CREATE VIEW `v_employees_job_titles` AS
SELECT concat_ws(' ', `first_name` , `middle_name` , `last_name`) AS 'full_name', `job_title`
FROM `employees`;
SELECT * FROM `v_employees_job_titles`;


#Task 17 - - -
SELECT DISTINCT `job_title` FROM `employees`
ORDER BY `job_title` ASC;


#Task 18 - - -
SELECT * FROM `projects`
ORDER BY `start_date` ASC, `name` ASC , `project_id` ASC
LIMIT 10;


#Task 19 - - -
SELECT `first_name` , `last_name` , `hire_date` FROM `employees`
ORDER BY `hire_date` DESC
LIMIT 7;


#Task 20 - - -
UPDATE `employees` SET `salary` = `salary` * 1.12
WHERE `department_id` IN (1,2,4,11);
SELECT `salary` FROM `employees`;


#Task 21 - - -
SELECT `peak_name` FROM `peaks`
ORDER BY `peak_name`ASC;


#Task 22 - - -
SELECT `country_name` , `population` FROM `countries`
WHERE `continent_code` = 'EU'
ORDER BY `population` DESC , `country_name` ASC
LIMIT 30;


#Task 23 - - -
SELECT `country_name` , `country_code` , IF(`currency_code` = 'EUR' , 'Euro' , 'Not Euro' ) AS 'currency' FROM `countries`
ORDER BY `country_name` ASC;


#Task 24 - - -
SELECT `name` FROM `characters`
ORDER BY `name` ASC;
