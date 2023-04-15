DROP TABLE IF EXISTS customerlab01; 

CREATE TABLE customerlab01(
	id		   		INT NOT NULL AUTO_INCREMENT,
	customerId   		VARCHAR(32) NOT NULL,
	PASSWORD   		VARCHAR(32) NOT NULL,
	NAME       		VARCHAR(50) NOT NULL,
	phone 			VARCHAR(20) NULL,
	birthday 		DATE NULL,
	registerdate 	DATETIME NOT NULL,
	picture 		LONGBLOB NULL,
	weight 			NUMERIC NULL,
	PRIMARY KEY(id)
);

INSERT INTO customerlab01 VALUES 
(NULL, 'kitty', 'k123', '凱蒂貓_my', '0919-852741', '1985-5-7', NOW(), NULL, 50.0), 
(NULL, 'mickey', 'm456', '米小薯_my', '0959-174885', '1992-12-22', NOW(), NULL, 46.4),  
(NULL, 'snoopy', 's789', '史努比_my', '0939-617501', '1997-8-14', NOW(), NULL, 55.6);
