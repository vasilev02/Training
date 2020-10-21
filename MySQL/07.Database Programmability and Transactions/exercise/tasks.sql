#Task 1 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN

	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name` ASC, `last_name` ASC, `employee_id` ASC;

END $$

CALL usp_get_employees_salary_above_35000();


#Task 2 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_param DECIMAL(10,4))
BEGIN

	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` >= salary_param
    ORDER BY `first_name` ASC, `last_name` ASC, `employee_id` ASC;

END $$

CALL usp_get_employees_salary_above(45000);


#Task 3 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with (string_param VARCHAR(45))
BEGIN
	
    SELECT `name` FROM `towns`
    WHERE `name` LIKE CONCAT(string_param , '%')
    ORDER BY `name` ASC;
    
END $$

CALL  usp_get_towns_starting_with ('b');


#Task 4 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town (town_name VARCHAR(45))
BEGIN
	
    SELECT `first_name`, `last_name`
    FROM `employees` AS e
    JOIN `addresses` AS a
    ON e.`address_id` = a.`address_id`
    JOIN `towns` AS t
    ON a.`town_id` = t.`town_id`
    WHERE t.`name` = town_name
    ORDER BY `first_name` ASC, `last_name` ASC, `employee_id` ASC;
    
END $$

CALL usp_get_employees_from_town ('Sofia');


#Task 5 - - -
DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary_param DOUBLE)
RETURNS VARCHAR(45)
DETERMINISTIC
BEGIN

	DECLARE result VARCHAR(45);
    
    IF (salary_param < 30000) THEN SET result = 'Low';
    ELSEIF(salary_param >= 30000 AND salary_param <= 50000) THEN SET result = 'Average';
    ELSEIF(salary_param > 50000) THEN SET result = 'High';
    END IF;
    
    RETURN result;

END $$

SELECT  ufn_get_salary_level(40550.00);


#Task 6 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level (level_param VARCHAR(10))
BEGIN
	
    IF(level_param = 'Low') THEN 
    SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` < 30000
    ORDER BY `first_name` DESC , `last_name` DESC;
    
    ELSEIF(level_param = 'Average') THEN 
    SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` BETWEEN 30000 AND 50000
    ORDER BY `first_name` DESC , `last_name` DESC;
    
    ELSEIF(level_param = 'High') THEN 
    SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` > 50000
    ORDER BY `first_name` DESC , `last_name` DESC;
    
    END IF;
    
END $$

CALL usp_get_employees_by_salary_level ('High');


#Task 7 - - -
DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT
DETERMINISTIC
BEGIN
	
		DECLARE indx INT;
        DECLARE symbol VARCHAR(1);
        SET indx := 1;
        
        WHILE indx <= CHAR_LENGTH(word) DO
        
        SET symbol := SUBSTRING(set_of_letters,indx,1);
        
        IF(LOCATE(symbol,word) = 0)
        THEN RETURN 0;
        END IF;
        
        SET indx := indx + 1;
        END WHILE;
    
    RETURN 1;
END $$

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia');


#Task 8 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	
    SELECT CONCAT(`first_name`,' ', `last_name`) AS `full_name` FROM `account_holders`
    ORDER BY `full_name` ASC, `id` ASC;
    
END $$

CALL usp_get_holders_full_name();


#Task 9 - - -
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (number_param DOUBLE)
BEGIN
	
    SELECT ah.`first_name`, ah.`last_name` FROM `account_holders` AS ah
    JOIN `accounts` AS a
    ON ah.id = a.account_holder_id
    WHERE a.balance < number_param
    GROUP BY ah.id
    ORDER BY ah.id ASC;
    
END $$

CALL usp_get_holders_with_balance_higher_than (7000);


#Task 10 - - -
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value (sum_param DECIMAL(19,4), interest_rate DOUBLE, years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN

	DECLARE result DECIMAL(19,4);
    
    SET result = sum_param * (POW((1 + interest_rate), years));
    
    RETURN result;

END $$

SELECT ufn_calculate_future_value (1000, 0.5, 5);


#Task 11 - - -
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account (id INT, interest_rate DOUBLE)
BEGIN
	
    SELECT ah.`id` , ah.`first_name`, ah.`last_name`, a.`balance` AS `current_balance`,
    (SELECT ufn_calculate_future_value(a.balance, interest_rate, 5)) AS `balance_in_5_years`
    FROM `account_holders` AS ah
    JOIN `accounts` AS a
    ON ah.`id` = a.`account_holder_id`
    WHERE ah.`id` = id;
    
END $$

CALL usp_calculate_future_value_for_account (1,0.1);


#Task 12 - - -
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT , money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF(money_amount <= 0) THEN ROLLBACK;
    END IF;
    
	UPDATE `accounts` AS a
    SET a.`balance` = a.`balance` + money_amount
    WHERE a.`id` = account_id;
    COMMIT;
    
END $$

CALL usp_deposit_money(1,10);


#Task 13 - - -
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT , money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF((SELECT `balance` FROM accounts WHERE id = account_id) - money_amount < 0) THEN ROLLBACK;
    END IF;
    
	UPDATE `accounts` AS a
    SET a.`balance` = a.`balance` - money_amount
    WHERE a.`id` = account_id;
    COMMIT;
    
END $$


#Task 14 - - -
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19,4)) 
BEGIN
	START TRANSACTION;
		UPDATE accounts SET balance = balance - amount
			WHERE id = from_account_id;
			UPDATE accounts SET balance = balance + amount
			WHERE id = to_account_id;
			
		IF((SELECT COUNT(*) FROM accounts
		      WHERE id = from_account_id) <> 1)
		   THEN ROLLBACK;
		ELSEIF(amount > (SELECT balance FROM accounts WHERE id = from_account_id))
			THEN ROLLBACK;
		ELSEIF(amount <= 0)
			THEN ROLLBACK;
		ELSEIF((SELECT balance FROM accounts WHERE id = from_account_id) <= 0)
			THEN ROLLBACK;	
		ELSEIF((SELECT COUNT(*) FROM accounts
		      WHERE id = to_account_id) <> 1)
		   THEN ROLLBACK;
		ELSEIF(amount <= 0)
			THEN ROLLBACK;
		ELSEIF(from_account_id = to_account_id)
			THEN ROLLBACK;
		ELSE 
			COMMIT;
		END IF;

END $$


#Task 15 - - -
CREATE TABLE `logs` (
	`log_id` INT AUTO_INCREMENT PRIMARY KEY, 
	`account_id` INT, 
	`old_sum` DECIMAL(19,4), 
	`new_sum` DECIMAL(19,4)
); 

DELIIMITER $$
CREATE TRIGGER `after_accounts_update`
AFTER UPDATE 
ON `accounts`
FOR EACH ROW
BEGIN
	INSERT INTO logs (account_id, old_sum, new_sum)
	VALUES (OLD.id, OLD.balance, NEW.balance);
END $$


#Task 16 - - -
CREATE TABLE `logs`(
	`log_id` INT AUTO_INCREMENT PRIMARY KEY,
	`account_id` INT,
	`old_sum` DECIMAL(19,4),
	`new_sum` DECIMAL(19, 4)
);
CREATE TABLE `notification_emails`(
	`id`INT AUTO_INCREMENT PRIMARY KEY,
	`recipient` INT,
	`subject` VARCHAR(50),
	`body` TEXT
);
DELIIMITER $$
CREATE TRIGGER `tr_emails`
AFTER UPDATE
ON `accounts`
FOR EACH ROW 
BEGIN
	INSERT INTO logs(account_id, old_sum, new_sum)
	VALUES(old.id, old.balance, new.balance);
	INSERT INTO notification_emails(recipient, subject, body)
	VALUES(
		old.id,
		CONCAT_WS(': ', 'Balance change for account', old.id),
		CONCAT('On ', NOW(), ' your balance was changed from ', old.balance, ' to ', new.balance, '.' ));
END $$