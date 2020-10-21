#Task 1 - - -
CREATE DATABASE `fsd`;

CREATE TABLE `countries`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE `towns`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45) NOT NULL,
`country_id` INT(11) NOT NULL,
CONSTRAINT `fk_towns_countries` FOREIGN KEY (`country_id`) REFERENCES `countries`(`id`)
);

CREATE TABLE `stadiums`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45) NOT NULL,
`capacity` INT(11) NOT NULL,
`town_id` INT(11) NOT NULL,
CONSTRAINT `fk_stadiums_towns` FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE `teams`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45) NOT NULL,
`established` DATE NOT NULL,
`fan_base` BIGINT(20) NOT NULL DEFAULT 0,
`stadium_id` INT(11) NOT NULL,
CONSTRAINT `fk_steams_stadiums` FOREIGN KEY (`stadium_id`) REFERENCES `stadiums`(`id`)
);

CREATE TABLE `coaches`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`coach_level` INT(11) NOT NULL DEFAULT 0
);

CREATE TABLE `skills_data`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`dribbling` INT(11) DEFAULT 0,
`pace` INT(11) DEFAULT 0,
`passing` INT(11) DEFAULT 0,
`shooting` INT(11) DEFAULT 0,
`speed` INT(11) DEFAULT 0,
`strength` INT(11) DEFAULT 0
);

CREATE TABLE `players`(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`age` INT(11) NOT NULL DEFAULT 0,
`position` CHAR(1) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`hire_date` DATETIME ,
`skills_data_id` INT(11) NOT NULL,
`team_id` INT(11),
CONSTRAINT `fk_players_teams` FOREIGN KEY (`team_id`) REFERENCES `teams`(`id`),
CONSTRAINT `fk_players_skillData` FOREIGN KEY (`skills_data_id`) REFERENCES `skills_data`(`id`)
);

CREATE TABLE `players_coaches`(
`player_id` INT(11) NOT NULL,
`coach_id` INT(11) NOT NULL,
CONSTRAINT `pk_players_coaches` PRIMARY KEY (`player_id`,`coach_id`),
CONSTRAINT `fk_pc_players` FOREIGN KEY (`player_id`) REFERENCES `players`(`id`),
CONSTRAINT `fk_pc_coaches` FOREIGN KEY (`coach_id`) REFERENCES `coaches`(`id`)
);


#Task 2 - - -

INSERT INTO `coaches` (`first_name`, `last_name`, `salary`, `coach_level`)
SELECT p.`first_name` AS 'first_name' ,
 p.`last_name` AS 'last_name', 
 p.`salary` * 2 AS 'salary',  
 CHAR_LENGTH(p.`first_name`) AS 'coach_level'
FROM `players` AS p
WHERE p.`age` >= 45;

SELECT * FROM coaches;


#Task 3 - - -
SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

UPDATE `coaches` AS c
SET c.`coach_level` = `coach_level` + 1
WHERE `id` IN (SELECT `coach_id` FROM `players_coaches`) AND LEFT(c.`first_name`,1) = 'A';


#Task 4 - - -
DELETE FROM `players`
WHERE `age` >= 45;

#Task 5 - - -
SELECT `first_name` , `age` , `salary` 
FROM `players`
ORDER BY `salary` DESC;


#Task 6 - - -
SELECT p.`id`, CONCAT(p.`first_name`,' ', p.`last_name`) AS `full_name`, p.`age`, p.`position`, p.`hire_date`
FROM `players` AS p
JOIN `skills_data` AS sd
ON p.`skills_data_id` = sd.`id`
WHERE p.`age` < 23 AND p.`hire_date` IS NULL AND p.`position` = 'A' AND sd.`strength` > 50
ORDER BY p.`salary` ASC, p.`age` ASC;


#Task 7 - - -
SELECT t.`name` AS `team_name`, t.`established`, t.`fan_base`, COUNT(p.`id`) AS `players_count`
FROM `teams`AS t
LEFT JOIN `players` AS p
ON t.`id` = p.`team_id`
GROUP BY t.`fan_base`
ORDER BY `players_count` DESC , t.`fan_base` DESC;


#Task 8 - - -
SELECT MAX(sd.speed) AS `max_speed`, t.`name` AS `town_name` FROM `towns` AS t
JOIN `stadiums` AS s
ON t.id = s.town_id
JOIN `teams` AS teamss
ON s.id = teamss.stadium_id
LEFT JOIN `players` AS p
ON p.team_id = teamss.id
LEFT JOIN `skills_data` AS sd
ON p.skills_data_id = sd.id
WHERE teamss.name NOT IN ('Devify')
GROUP BY t.`name`
ORDER BY `max_speed` DESC, `town_name` ASC;


#Task 9 - - -
SELECT c.`name`, COUNT(p.id) AS `total_count_of_players`, SUM(p.salary) AS `total_sum_of_salaries` FROM `countries` AS c
LEFT JOIN `towns` AS t
ON c.id = t.country_id
LEFT JOIN `stadiums` AS s
ON t.id = s.town_id
LEFT JOIN `teams` AS teamss
ON s.id = teamss.stadium_id
LEFT JOIN `players` AS p
ON p.team_id = teamss.id
GROUP BY c.`name`
ORDER BY `total_count_of_players` DESC, c.`name` ASC;


#Task 10 - - -
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count(stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	
    DECLARE p_count INT;
    
    SET p_count := (
    
    SELECT COUNT(*) FROM `stadiums` AS s
    JOIN `teams` AS t
    ON s.id = t.stadium_id
    JOIN `players` AS p
    ON t.id = p.team_id
    WHERE s.`name` = stadium_name
    );
    
    RETURN p_count;
    
END$$

SELECT udf_stadium_players_count ('Jaxworks') as `count`;
SELECT udf_stadium_players_count ('Linklinks') as `count`;


#Task 11 - - -
DELIMITER $$
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
	
    SELECT CONCAT(p.`first_name`,' ', p.`last_name`) AS `full_name`, p.age, p.`salary`, sd.`dribbling`, sd.`speed`, t.`name` AS `team_name` FROM `players` AS p
    JOIN `teams` AS t
    ON p.team_id = t.id
    JOIN `skills_data` AS sd
    ON p.skills_data_id = sd.id
    WHERE sd.dribbling > min_dribble_points AND t.name = team_name
    AND sd.speed > (
    
    SELECT AVG(skd.speed) FROM `players` AS pl
    JOIN `skills_data` AS skd
    ON pl.skills_data_id = skd.id
    
    )
    ORDER BY sd.speed DESC 
    limit 1;
    
END$$

CALL udp_find_playmaker (20, 'Skyble');
