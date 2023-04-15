IF OBJECT_ID('dbo.customerlab01', 'U') IS NOT NULL 
  DROP TABLE dbo.customerlab01; 

CREATE TABLE customerlab01(
	id		   		INT NOT NULL IDENTITY,
	customerId   		varchar(32) NOT NULL,
	password   		varchar(32) NOT NULL,
	name       		varchar(50) NOT NULL,
	phone 			varchar(20) NULL,
	birthday 		date NULL,
	registerdate 	datetime NOT NULL,
	picture 		image NULL,
	weight 			numeric NULL,
	PRIMARY KEY(id)
)

INSERT INTO customerlab01 
(customerId, password, name, phone, birthday, registerdate, picture, weight) 
VALUES 
('kitty', 'k123', '凱蒂貓_ms', '0919-852741', '1985-5-7', GETDATE(), NULL, 50.0), 
('mickey', 'm456', '米小薯_ms', '0959-174885', '1992-12-22', GETDATE(), NULL, 46.4),  
('snoopy', 's789', '史努比_ms', '0939-617501', '1997-8-14', GETDATE(), NULL, 55.6);


