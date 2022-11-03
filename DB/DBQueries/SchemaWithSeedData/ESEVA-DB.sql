IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'ESEVA')
BEGIN
	CREATE DATABASE ESEVA
END
GO

USE ESEVA
GO


IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='STATUS' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE STATUS 
	(
	StatusID int IDENTITY(1,1) PRIMARY KEY,
	StatusName varchar(15) NOT NULL
	)

	INSERT INTO ESEVA.DBO.STATUS
	(
		StatusName
	)
	VALUES
	('ACTIVE'),
	('INACTIVE'),
	('PENDING'),
	('APPROVED'),
	('REJECTED')
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='ROLE' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE ROLE
	(
	RoleID int IDENTITY(1,1) PRIMARY KEY,
	RoleName varchar(15) NOT NULL
	)

	INSERT INTO ESEVA.DBO.ROLE
	(RoleName)
	VALUES
	('CITIZEN'),
	('VENDOR'),
	('ADMIN')
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='LANGUAGE' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE LANGUAGE
	(LanguageID int IDENTITY(1,1) PRIMARY KEY,
	LanguageName varchar(15) NOT NULL)

	INSERT INTO LANGUAGE
	(LanguageName)
	VALUES
	('Hindi'),
	('English'),
	('Bengali'),
	('Marathi'),
	('Telugu'),
	('Tamil'),
	('Gujarati'),
	('Urdu'),
	('Kannada'),
	('Odia'),
	('Malayalam'),
	('Punjabi'),
	('Assamese'),
	('Maithili'),
	('Meitei'),
	('Sanskrit')
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='SERVICE' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE SERVICE
	(
	ServiceId int IDENTITY(1,1) PRIMARY KEY,
	ServiceName varchar(50) NOT NULL,
	ServiceDescription varchar(250) NOT NULL,
	ServiceImgLoc varchar(100) NOT NULL
	)

	INSERT INTO SERVICE 
	(ServiceName,ServiceDescription,ServiceImgLoc)
	VALUES
	('Fastag','We are authorized Corporate Partner for FASTag product in India. We have the facility of FASTag Retail Agent for distributing.','/service-fastag.png'),
	('Pan Card','We are leading PAN card agency provider and with us you can start an online / authorized PAN Card Center of the UTIITSL.','/service-pan-card.png'),
	('Digital Signature','e-Seva is designed to help partners offer DSC services without large capital associated with building and maintaining.','/service-digital-signature.jpg'),
	('Ayushman Card','The Pradhan Mantri Jan Arogya Yojana (PMJAY) popularly known as Ayushman Bharat Yojana Scheme.','/service-ayushman-yojna.jpeg'),
	('ITR Service','Income Tax Return (ITR) is a form in which the taxpayers file information about his income earned and tax applicable to.','/service-itr_service.png'),
	('GST Suvidha','GST Suvidha- GST Suvidha is considered as an enabler or authorised intermediary for businesses to access GST portal services.','/service-gst.png')
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='DOCUMENT' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE DOCUMENT
	(
	DocumentId varchar(50) PRIMARY KEY NOT NULL,
	ParentDocumentId varchar(50) FOREIGN KEY REFERENCES DOCUMENT(DocumentId),
	DocumentInfo varbinary(MAX) 
	)
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='USER' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE "USER"
	(
		UserId varchar(20) PRIMARY KEY NOT NULL,
		RoleId int FOREIGN KEY REFERENCES ROLE(RoleId),
		UserName varchar(30) NOT NULL,
		DocumentId varchar(50) FOREIGN KEY REFERENCES DOCUMENT(DocumentId),
		StatusId int FOREIGN KEY REFERENCES STATUS(StatusId),
		LanguageId int FOREIGN KEY REFERENCES LANGUAGE(LanguageId),
		PhoneNumber varchar(15),
		EmailId varchar(330) NOT NULL UNIQUE,
		Password varchar(100) NOT NULL,
		CreatedDate DATETIME2 NOT NULL,
		ModifiedDate DATETIME2 NOT NULL,
		UpdatedBy varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
		LoginAttempts int DEFAULT (0) CHECK(LoginAttempts <=5 AND LoginAttempts >=0)
	)

	INSERT INTO DOCUMENT
	(DocumentId, ParentDocumentId, DocumentInfo)
	VALUES
	('63E7C3E0-FF93-4A47-BB9A-610C3F9838FF','63E7C3E0-FF93-4A47-BB9A-610C3F9838FF',convert(varbinary, ''))



	INSERT INTO "USER"
	(UserId,RoleId,UserName,DocumentId,StatusId,LanguageId,PhoneNumber,EmailId,Password,CreatedDate,ModifiedDate,UpdatedBy,LoginAttempts)
	VALUES
	('admin',3,'Admin User','63E7C3E0-FF93-4A47-BB9A-610C3F9838FF',	1,	1,'9876543210', 'admin@email.com','4B9DB269C5F978E1264480B0A7619EEA','2022-11-03 04:06:51.3666667',	'2022-11-03 04:06:51.3666667','admin',0)

END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='SESSION' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE SESSION
	(
		SessionID varchar(50) UNIQUE NOT NULL,
		UserId varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
		StatusId int FOREIGN KEY REFERENCES STATUS(StatusID),
		CreatedDate DATETIME2 NOT NULL,
		ModifiedDate DATETIME2 NOT NULL,
		ExpiryDate DATETIME2 NOT NULL
	)
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='SERVICEREQUEST' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE SERVICEREQUEST
	(
	ServiceRequestId varchar(50) PRIMARY KEY NOT NULL,
	UpdatedBy varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
	RequestedBy varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
	RequestedFor varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
	ServiceId int FOREIGN KEY REFERENCES SERVICE(ServiceId),
	StatusId int FOREIGN KEY REFERENCES STATUS(StatusId),
	DocumentId varchar(50) FOREIGN KEY REFERENCES DOCUMENT(DocumentId),
	LanguageId int FOREIGN KEY REFERENCES LANGUAGE(LanguageId),
	ServiceRequestDescription nvarchar(4000) NOT NULL,
	CreatedDate DATETIME2 NOT NULL,
	ModifiedDate DATETIME2 NOT NULL
	)
END
GO