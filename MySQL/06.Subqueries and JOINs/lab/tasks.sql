#Task 1 - - -
SELECT e.`employee_id`, CONCAT(e.`first_name`, ' ' , e.`last_name`) AS 'full_name', d.`department_id` , d.`name` AS  'department_name'
FROM `employees` AS e
INNER JOIN `departments` AS d
ON e.`employee_id` = d.`manager_id`
ORDER BY `employee_id`
LIMIT 5;


#Task 2 - - -
SELECT a.`town_id`, t.`name`, a.`address_text` FROM `addresses` AS a
JOIN `towns` AS t
ON a.`town_id` = t.`town_id`
WHERE t.`name` IN ('San Francisco', 'Sofia', 'Carnation')
ORDER BY t.`town_id` ASC, a.`address_id` ASC;


#Task 3 - - -
SELECT `employee_id`, `first_name`, `last_name`, `department_id`, `salary`
FROM `employees`
WHERE `manager_id` IS NULL;


#Task 4 - - -
SELECT COUNT(*) as 'count' FROM `employees`
WHERE `salary` >
(
SELECT AVG(`salary`) FROM `employees`
)
