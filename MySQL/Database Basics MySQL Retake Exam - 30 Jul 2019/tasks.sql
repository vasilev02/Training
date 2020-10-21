#Task 1 - - -
CREATE DATABASE `colonial_blog_db`;

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category` VARCHAR(30) NOT NULL
);

CREATE TABLE `articles`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`content` TEXT NOT NULL,
`category_id` INT,
CONSTRAINT `fk_articles_categories` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

CREATE TABLE `users`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(30) NOT NULL,
`email` VARCHAR(50) NOT NULL
);

CREATE TABLE `users_articles`(
`user_id` INT,
`article_id` INT,
CONSTRAINT `fk_userArticles_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
CONSTRAINT `fk_userArticles_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`)
);

CREATE TABLE `comments`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`comment` VARCHAR(255) NOT NULL,
`article_id` INT NOT NULL,
`user_id` INT NOT NULL,
CONSTRAINT `fk_comments_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`),
CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `likes`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`article_id` INT,
`comment_id` INT,
`user_id` INT NOT NULL,
CONSTRAINT `fk_likes_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`),
CONSTRAINT `fk_likes_comments` FOREIGN KEY (`comment_id`) REFERENCES `comments`(`id`),
CONSTRAINT `fk_likes_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

SELECT COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
AND COLUMN_NAME IN ('id','product_id','store_id',
 'town_id', 'address_id', 'category_id', 'picture_id','manager_id')
ORDER BY TABLE_NAME, COLUMN_KEY;


#Task 2 - - -
INSERT INTO `likes`(`article_id`, `comment_id`, `user_id`)
SELECT

	IF(u.`id` % 2 = 0 , CHAR_LENGTH(u.`username`),null),
    
	IF(u.`id` % 2 = 1 , CHAR_LENGTH(u.`email`),null),
    
    u.`id`

FROM `users` AS u
WHERE u.`id` BETWEEN 16 AND 20;

#Task 3 - - -

SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

UPDATE `comments` AS c
SET c.`comment` = (

CASE
    WHEN c.`id` % 2 = 0 THEN 'Very good article.'
    WHEN c.`id` % 3 = 0 THEN 'This is interesting.'
    WHEN c.`id` % 5 = 0 THEN 'I definitely will read the article again.'
    WHEN c.`id` % 7 = 0 THEN 'The universe is such an amazing thing.'
    ELSE c.`comment`
END
)
WHERE c.`id` BETWEEN 1 AND 15;


#Task 4 - - -

DELETE FROM `articles`
WHERE `category_id` IS NULL;

#Task 5 - - -
SELECT a.id, a.title, CONCAT(LEFT(a.content, 20), '...') AS `summary` 
FROM `articles` AS a
ORDER BY CHAR_LENGTH(a.content) DESC, a.`id` ASC
LIMIT 3;

SELECT a.title, CONCAT(LEFT(a.content, 20), '...') AS `summary` 
FROM 
(SELECT `id`, `title`, `content` 
FROM `articles`
ORDER BY CHAR_LENGTH(`content`) DESC
LIMIT 3
) AS `a`
ORDER BY a.`id` ASC
LIMIT 3;


#Task 6 - - -
SELECT a.`id` AS `article_id`, a.`title`
FROM `articles` AS a
JOIN `users_articles` AS ua
ON a.id = ua.article_id
JOIN `users` AS u
ON ua.user_id = u.id
WHERE a.`id` = u.`id`
ORDER BY a.`id` ASC;


#Task 7 - - -
SELECT c.`category`, COUNT(DISTINCT a.`id`) AS `articles`, COUNT(DISTINCT l.`id`) AS `likes`
FROM `categories` AS c
LEFT JOIN `articles` AS a
ON c.id = a.category_id
LEFT JOIN `likes` AS l
ON a.id = l.article_id
GROUP BY c.`category`
ORDER BY `likes` DESC, `articles` DESC, c.`id` ASC;


#Task 8 - - -
SELECT a.`title`, COUNT(c.`id`) AS `comments`
FROM `articles` AS a
JOIN `comments` AS c
ON a.id = c.article_id
WHERE a.`category_id` = 5
GROUP BY a.`id`
ORDER BY COUNT(c.`id`) DESC
LIMIT 1;


#Task 9 - - -
SELECT CONCAT(LEFT(c.`comment`, 20), '...') AS `summary`
FROM `comments` AS c
LEFT JOIN `likes` AS l
ON c.id = l.comment_id
WHERE l.`id` IS NULL
ORDER BY c.`id` DESC;


#Task 10 - - -
DELIMITER $$
CREATE FUNCTION udf_users_articles_count(username VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	
    DECLARE a_count INT;
    
    SET a_count := (
    
    SELECT COUNT(a.`id`) 
    FROM `users` AS u
    LEFT JOIN `users_articles` ua
    ON u.id = ua.user_id
    LEFT JOIN `articles` AS a
    ON ua.article_id = a.id
    WHERE u.`username` = username
    );
    
    RETURN a_count;
    
END $$

SELECT u.username, udf_users_articles_count('UnderSinduxrein') AS count
FROM articles AS a
JOIN users_articles ua
ON a.id = ua.article_id
JOIN users u
ON ua.user_id = u.id
WHERE u.username = 'UnderSinduxrein'
GROUP BY u.id;


#Task 11 - - -
DELIMITER $$
CREATE PROCEDURE udp_like_article(username_prop VARCHAR(30), title_prop VARCHAR(30))
BEGIN
	
    START TRANSACTION;
    IF((SELECT u.`username` FROM `username`) != username_prop) 
    
    THEN 
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Non-existent user.' ;
    
    ROLLBACK;
    
    ELSEIF((SELECT a.`title` FROM `articles`) != title_prop)
    
    THEN 
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Non-existent article.' ;
    
    ROLLBACK;
    
    ELSE
    
    INSERT INTO `likes`(`article_id`,`user_id`)
    (SELECT a.id , u.id
    FROM `users` AS u
	JOIN `articles` AS a
    WHERE u.username = username_prop AND a.title = title_prop);

	COMMIT;
    
    END IF;
    
END $$

CALL udp_like_article('Pesho123', 'Donnybrook, Victoria');

CALL udp_like_article('BlaAntigadsa', 'Donnybrook, Victoria');
SELECT a.title, u.username 
FROM articles a 
JOIN likes l
ON a.id = l.article_id
JOIN users u
ON l.user_id = u.id
WHERE u.username = 'BlaAntigadsa' AND a.title = 'Donnybrook, Victoria';

