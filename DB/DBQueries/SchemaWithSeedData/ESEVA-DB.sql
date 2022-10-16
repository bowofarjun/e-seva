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
	ServiceDescription varchar(250) NOT NULL
	)

	INSERT INTO SERVICE 
	(ServiceName,ServiceDescription)
	VALUES
	('Request New PAN Card','Through E-Seva, you can now request for a new PAN card.'),
	('Update PAN Card Details','Through E-Seva, you can now raise a request for updating the details in your existing PAN card.')
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
		CreatedDate date NOT NULL,
		ModifiedDate date NOT NULL,
		UpdatedBy varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
		LoginAttempts int DEFAULT (0) CHECK(LoginAttempts <=5 AND LoginAttempts >=0)
	)
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='SESSION' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE SESSION
	(
		SessionID varchar(50) UNIQUE NOT NULL,
		UserId varchar(20) FOREIGN KEY REFERENCES "USER"(UserId),
		StatusId int FOREIGN KEY REFERENCES STATUS(StatusID),
		CreatedDate date NOT NULL,
		ModifiedDate date NOT NULL,
		ExpiryData date NOT NULL
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
	CreatedDate date NOT NULL,
	ModifiedDate date NOT NULL
	)
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='NOTIFICATION' and TABLE_CATALOG='ESEVA')
BEGIN
	CREATE TABLE NOTIFICATION
	(
		NotificationId varchar(50) PRIMARY KEY NOT NULL,
		receiverEmailId varchar(330) NOT NULL FOREIGN KEY REFERENCES "USER"(EmailId),
		SenderEmailId varchar(330) NOT NULL,
		ServiceRequestId varchar(50) FOREIGN KEY REFERENCES SERVICEREQUEST(ServiceRequestId)
	)
END
GO

IF NOT EXISTS(select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='FEEDBACK' and TABLE_CATALOG='ESEVA' )
BEGIN
	CREATE TABLE FEEDBACK
	(
		FeedbackId varchar(50) PRIMARY KEY NOT NULL,
		FeedbackMessage varchar(500),
		FeedbackSubject varchar(100) NOT NULL,
		UserId varchar(20) FOREIGN KEY REFERENCES "USER"(UserId)
	)
END
GO