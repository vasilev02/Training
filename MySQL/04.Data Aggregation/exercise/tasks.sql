#Task 1 - - -
SELECT COUNT(*) AS `count`
FROM `wizzard_deposits`;


#Task 2 - - -
SELECT MAX(`magic_wand_size`) AS `longest_magic_wand`
FROM `wizzard_deposits`;


#Task 3 - - -
SELECT `deposit_group` , MAX(`magic_wand_size`) AS `longest_magic_wand`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand` ASC , `deposit_group` ASC;


#Task 4 - - -
SELECT `deposit_group`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY AVG(`magic_wand_size`) ASC
LIMIT 1;


#Task 5 - - -
SELECT `deposit_group` , SUM(`deposit_amount`) AS `total_sum`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum` ASC;


#Task 6 - - -
SELECT `deposit_group` , SUM(`deposit_amount`) AS `total_sum`
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
ORDER BY `deposit_group`;


#Task 7 - - -
SELECT `deposit_group` , SUM(`deposit_amount`) AS `total_sum`
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;


#Task 8 - - -
SELECT `deposit_group` , `magic_wand_creator` , MIN(`deposit_charge`) AS `min_deposit_charge`
FROM `wizzard_deposits`
GROUP BY `deposit_group` , `magic_wand_creator`
ORDER BY `magic_wand_creator` ASC , `deposit_group` ASC;


#Task 9 - - -
SELECT
	CASE
		WHEN `age` <= 10 THEN '[0-10]'
		WHEN `age` >= 11 AND `age` <= 20 THEN '[11-20]'
		WHEN `age` >= 21 AND `age` <= 30 THEN '[21-30]'
		WHEN `age` >= 31 AND `age` <= 40 THEN '[31-40]'
		WHEN `age` >= 41 AND `age` <= 50 THEN '[41-50]'
		WHEN `age` >= 51 AND `age` <= 60 THEN '[51-60]'
        ELSE '[61+]'
    END AS 'age_group' , COUNT(`age`)
    FROM `wizzard_deposits`
    GROUP BY `age_group`
    ORDER BY `age_group` ASC;
    
    
#Task 10 - - -
SELECT DISTINCT SUBSTRING(`first_name`,1,1)
FROM `wizzard_deposits`
WHERE `deposit_group` = 'Troll Chest'
ORDER BY `first_name` ASC;


#Task 11 - - -
SELECT `deposit_group` , `is_deposit_expired` , AVG(`deposit_interest`) AS 'average_interest'
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985/01/01'
GROUP BY `is_deposit_expired` , `deposit_group`
ORDER BY `deposit_group` DESC , `is_deposit_expired`;


#Task 12 - - -
SELECT SUM(diff.next) AS 'sum_difference'

FROM (SELECT `deposit_amount` - 
								(SELECT `deposit_amount`
                                FROM `wizzard_deposits`
                                WHERE `id` = firstWizard.id +1) AS 'next'
	 FROM `wizzard_deposits` AS firstWizard ) AS diff;


#Task 13 - - -
SELECT `department_id` , MIN(`salary`) as 'minimum_salary'
FROM `employees`
WHERE `department_id` IN (2,5,7)
GROUP BY `department_id`
ORDER BY `department_id` ASC;


#Task 14 - - -
CREATE TABLE `new_table` AS
SELECT * FROM `employees`
WHERE `salary` > 30000;

DELETE  FROM `new_table` WHERE `manager_id` = 42;

UPDATE `new_table`
SET `salary` = `salary` + 5000
WHERE `department_id` = 1;

SELECT `department_id` , AVG(`salary`)
FROM `new_table`
GROUP BY `department_id`
ORDER BY `department_id` ASC;

#Task 15 - - -
SELECT `department_id` , MAX(`salary`) AS `max_salary`
FROM `employees`
GROUP BY `department_id`
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY `department_id` ASC;


#Task 16 - - -
SELECT COUNT(*)
FROM `employees`
WHERE `manager_id` IS NULL;


#Task 17 - - -
SELECT e.`department_id`, (SELECT d.`salary`
FROM `employees` AS d
WHERE  d.`department_id` = e.`department_id`
GROUP BY d.`salary`
ORDER BY d.`salary` DESC
LIMIT 1 OFFSET 2) AS `salary`
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING `salary` IS NOT NULL
ORDER BY e.`department_id`;


#Task 18 - - -
SELECT e.first_name , e.last_name , e.department_id
FROM `employees` AS e , (SELECT emp.department_id , AVG(emp.salary) as avg_salary
						FROM `employees` AS emp
                        GROUP BY emp.department_id) AS inner_department
WHERE e.department_id = inner_department.department_id AND e.salary > inner_department.avg_salary
ORDER BY e.department_id ASC, e.employee_id ASC
LIMIT 10;


#Task 19 - - -
SELECT `department_id` , SUM(`salary`) AS 'total_salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id` ASC;