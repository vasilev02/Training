#Task 1 - - -
SELECT e.`employee_id`, e.`job_title`, d.`address_id`, d.`address_text`
FROM `employees` as e
JOIN `addresses` AS d
ON e.`address_id` = d.`address_id`
ORDER BY d.`address_id` ASC
LIMIT 5;


#Task 2 - - -
SELECT e.`first_name`, e.`last_name`, t.`name`, a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
JOIN `towns` AS t
ON t.`town_id` = a.`town_id`
ORDER BY e.`first_name` ASC , e.`last_name` ASC
LIMIT 5;


#Task 3 - - -
SELECT e.`employee_id`, e.`first_name`, e.`last_name`, d.`name`
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;


#Task 4 - - -
SELECT e.`employee_id`, e.`first_name`, e.`salary`, d.`name`
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE e.`salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;


#Task 5 - - -
SELECT e.`employee_id`, e.`first_name`
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
WHERE ep.`project_id` IS  NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;


#Task 6 - - -
SELECT e.`first_name`, e.`last_name`, e.`hire_date`, d.`name` AS `dept_name`
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE DATE(e.`hire_date`) > '1/1/1999' AND d.`name` IN ('Sales', 'Finance')
ORDER BY e.`hire_date` ASC;


#Task 7 - - -
SELECT e.`employee_id`, e.`first_name`, p.`name`
FROM `employees` AS e
JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
JOIN `projects`AS p
ON ep.`project_id` = p.`project_id`
WHERE DATE(p.start_date) > '2002-08-13' AND p.end_date IS NULL
ORDER BY e.`first_name` ASC , p.`name` ASC
LIMIT 5;


#Task 8 - - -
SELECT e.`employee_id`, e.`first_name`,
CASE
	WHEN YEAR(p.`start_date`) >= 2005 THEN p.name = null
    ELSE p.`name`
END AS `project_name`
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep
ON e.`employee_id` = ep.`employee_id`
LEFT JOIN `projects` AS p
ON ep.`project_id` = p.`project_id`
WHERE e.`employee_id` = 24
ORDER BY p.`name` ASC;


#Task 9 - - -
SELECT e.`employee_id`, e.`first_name`, e.`manager_id`, e2.`first_name`
FROM `employees` AS e
JOIN `employees` AS e2
ON e.`manager_id` = e2.`employee_id`
WHERE e.`manager_id` IN (3, 7)
ORDER BY e.`first_name`;


#Task 10 - - -
SELECT e.`employee_id`, CONCAT(e.first_name , ' ' , e.last_name) AS `employee_name`, CONCAT(e2.first_name , ' ' , e2.last_name) AS `manager_name` , d.`name`
FROM `employees` AS e
JOIN `employees` AS e2
ON e.`manager_id` = e2.`employee_id`
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
ORDER BY e.`employee_id`
LIMIT 5;


#Task 11 - - -
SELECT MIN(`min_sal`) AS `min_average_salary`
FROM
(
SELECT AVG(`salary`) AS `min_sal`
FROM `employees`
GROUP BY `department_id`
) AS `min_salary`;


#Task 12 - - -
SELECT c.`country_code`, m.`mountain_range`, p.`peak_name`, p.`elevation`
FROM `countries` AS c
JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
JOIN `mountains` AS m
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`
WHERE c.`country_name` = 'Bulgaria' AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;


#Task 13 - - -
SELECT mc.`country_code`, COUNT(*) AS `mountain_range`
FROM `mountains_countries` AS mc
JOIN `mountains` AS  m
ON mc.`mountain_id` = m.`id`
WHERE mc.`country_code` IN ('US' , 'RU', 'BG')
GROUP BY mc.country_code
ORDER BY `mountain_range` DESC;


#Task 14 - - -
SELECT c.`country_name`, r.`river_name`
FROM `countries` AS c
LEFT JOIN `countries_rivers` AS cr
ON c.`country_code` = cr.`country_code`
LEFT JOIN `rivers` AS r
ON cr.`river_id` = r.`id`
WHERE c.`continent_code` = 'AF'
ORDER BY c.`country_name` ASC
LIMIT 5;


#Task 15 - - -
SELECT d1.continent_code, d1.currency_code, d1.currency_usage FROM 
	(SELECT `c`.`continent_code`, `c`.`currency_code`,
    COUNT(`c`.`currency_code`) AS `currency_usage` FROM countries as c
	GROUP BY c.currency_code, c.continent_code HAVING currency_usage > 1) as d1
LEFT JOIN 
	(SELECT `c`.`continent_code`,`c`.`currency_code`,
    COUNT(`c`.`currency_code`) AS `currency_usage` FROM countries as c
	 GROUP BY c.currency_code, c.continent_code HAVING currency_usage > 1) as d2
ON d1.continent_code = d2.continent_code AND d2.currency_usage > d1.currency_usage
 
WHERE d2.currency_usage IS NULL
ORDER BY d1.continent_code, d1.currency_code;


#Task 16 - - -
SELECT COUNT(*) AS 'country_count'
FROM `countries` AS c
LEFT JOIN `mountains_countries` AS mc
ON c.`country_code` = mc.`country_code`
WHERE mc.`country_code` IS NULL;

#Task 17 - - -
SELECT c.`country_name`, MAX(p.elevation) AS `highest_peak_elevation`, MAX(r.length) AS `longest_river_length`
FROM `countries` AS c
LEFT JOIN `countries_rivers` AS cr
ON c.country_code = cr.country_code
LEFT JOIN `rivers` AS r
ON cr.river_id = r.id
LEFT JOIN `mountains_countries` AS mc
ON c.country_code = mc.country_code
LEFT JOIN `mountains` AS m
ON mc.mountain_id = m.id
LEFT JOIN `peaks` AS p
ON m.id = p.mountain_id
GROUP BY c.country_name
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC, c.`country_name` ASC
LIMIT 5;

