#Task 1 - - -
SELECT `department_id` , count(`id`) AS `Number of employees`
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id` ASC , `Number of employees`  ASC;


#Task 2 - - -
SELECT `department_id` , ROUND(AVG(`salary`), 2) AS `Average Salary`
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;


#Task 3 - - -
SELECT `department_id` , ROUND(MIN(`salary`), 2) AS `Min Salary`
FROM `employees`
GROUP BY `department_id`
HAVING `Min Salary` > 800;


#Task 4 - - -
SELECT count(`name`)
FROM `products`
WHERE `category_id` = 2 AND `price` > 8;


#Task 5 - - -
SELECT `category_id` ,
ROUND(AVG(`price`), 2) AS `Average Price`,
ROUND(MIN(`price`), 2) AS `Cheapest Price`,
ROUND(MAX(`price`), 2) AS `Most Expensive Price`
FROM `products`
GROUP BY `category_id`;