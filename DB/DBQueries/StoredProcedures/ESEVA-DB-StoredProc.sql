USE ESEVA
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_STATUSES]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_STATUSES]
END
GO

CREATE PROCEDURE [dbo].[GET_ALL_STATUSES]
AS
	SELECT StatusId, StatusName FROM ESEVA.dbo.STATUS
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_ROLES]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_ROLES]
END
GO

CREATE PROCEDURE [dbo].[GET_ALL_ROLES]
AS
	SELECT RoleId, RoleName FROM ESEVA.dbo.ROLE
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_LANGUAGES]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_LANGUAGES]
END
GO

CREATE PROCEDURE [dbo].[GET_ALL_LANGUAGES]
AS
	SELECT LanguageId, LanguageName FROM ESEVA.dbo.LANGUAGE
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_SERVICES]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_SERVICES]
END
GO

CREATE PROCEDURE [dbo].[GET_ALL_SERVICES]
AS
	SELECT ServiceId, ServiceName, ServiceDescription FROM ESEVA.dbo.SERVICE
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[ADD_NEW_USER]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[ADD_NEW_USER]
END
GO

CREATE PROCEDURE ADD_NEW_USER
@UserId varchar(20),
@RoleId int,
@UserName varchar(30),
@LanguageId int,
@PhoneNumber varchar(15),
@EmailId varchar(330),
@Password varchar(100),
@Document varbinary(max),
@DocumentId varchar(50) OUTPUT,
@StatusName varchar(15) OUTPUT,
@errorCode int OUTPUT,
@errorMessage nvarchar OUTPUT
AS
	BEGIN TRY
		BEGIN TRAN
		    Declare @currTime DATE
            Declare @StatusID int
			set @currTime = GETUTCDATE();
			set @DocumentId = NEWID();
            set @StatusID = 3;
            set @errorCode = 0;
            set @errorMessage = NULL
            SELECT @StatusName=StatusName FROM ESEVA.DBO.STATUS WHERE StatusID=@StatusID
			INSERT INTO ESEVA.DBO.DOCUMENT
			(DocumentId, DocumentInfo, ParentDocumentId)
			VALUES
			(@DocumentId, @Document, @DocumentId)

			INSERT INTO ESEVA.DBO."USER"
			(UserId, RoleId, UserName, DocumentId, StatusId, LanguageId, PhoneNumber, EmailId, Password, CreatedDate, ModifiedDate, UpdatedBy)
			VALUES
			(@UserId, @RoleId, @UserName, @DocumentId, @StatusID, @LanguageId, @PhoneNumber, @EmailId, @Password, @currTime, @currTime, @UserId)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
        set @errorCode = ERROR_NUMBER()
        set @errorMessage = ERROR_MESSAGE()
		ROLLBACK TRAN
	END CATCH
GO
