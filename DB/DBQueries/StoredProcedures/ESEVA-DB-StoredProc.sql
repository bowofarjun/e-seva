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
	SELECT ServiceId, ServiceName, ServiceDescription, ServiceImgLoc FROM ESEVA.dbo.SERVICE
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
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
		BEGIN TRAN
		    Declare @currTime DATETIME2
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

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[VALIDATE_USER_LOGIN]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[VALIDATE_USER_LOGIN]
END
GO

CREATE PROCEDURE VALIDATE_USER_LOGIN
@UserId varchar(20),
@Password varchar(100),
@isLoginSuccessful int OUTPUT,
@UserName varchar(30) OUTPUT,
@LoginAttempts int OUTPUT,
@RoleName varchar(15) OUTPUT,
@SessionId varchar(50) OUTPUT,
@StatusName varchar(15) OUTPUT,
@errorCode int OUTPUT,
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
		BEGIN TRAN
			DECLARE @toMatchPassword varchar(100)
			DECLARE @currTime Datetime2
			DECLARE @expTime Datetime2
		    set @errorCode=0
			set @errorMessage=NULL
			set @isLoginSuccessful=0
			set @LoginAttempts=0
			set @currTime = GETUTCDATE()
			select @expTime=dateadd(minute, 15, @currTime)
			SELECT @toMatchPassword=U.Password, @LoginAttempts=U.LoginAttempts, @RoleName=R.RoleName, @StatusName=S.StatusName, @UserName=U.UserName FROM ESEVA.DBO."USER" AS U 
			JOIN
			ESEVA.DBO.ROLE AS R
			ON R.RoleID=U.RoleId
			JOIN 
			ESEVA.DBO.STATUS AS S
			ON S.StatusID=U.StatusID
			WHERE UserId=@UserId

			IF (@StatusName='ACTIVE')
				BEGIN
					IF(@Password = @toMatchPassword)
						BEGIN
							set @isLoginSuccessful=1
							set @LoginAttempts=0
							UPDATE ESEVA.DBO."USER"
							SET LoginAttempts=@LoginAttempts
							WHERE UserId=@UserId

							set @SessionId = NEWID()
							INSERT INTO ESEVA.DBO.SESSION
							(SessionID,UserId,StatusId,CreatedDate,ModifiedDate,ExpiryDate)
							VALUES
							(@SessionId,@UserId,1,@currTime,@currTime,@expTime)
						END
					ELSE
						BEGIN
                            set @LoginAttempts=@LoginAttempts+1;
							UPDATE ESEVA.DBO."USER"
							SET LoginAttempts=@LoginAttempts
							WHERE UserId=@UserId
						END
			    END
		COMMIT TRAN
	END TRY
	BEGIN CATCH
        set @errorCode = ERROR_NUMBER()
        set @errorMessage = ERROR_MESSAGE()
		ROLLBACK TRAN
		IF(@LoginAttempts=6)
			BEGIN
	    		Update ESEVA.DBO."USER"
				set StatusID=2
				where UserId=@UserId
			END
	END CATCH
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[VALIDATE_USER_SESSION]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[VALIDATE_USER_SESSION]
END
GO

CREATE PROCEDURE VALIDATE_USER_SESSION
@UserId varchar(20),
@SessionId varchar(50),
@StatusName varchar(15) OUTPUT,
@errorCode int OUTPUT,
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
	    BEGIN TRAN
			DECLARE @currTime DATETIME2
			DECLARE @createdDate DATETIME2
			DECLARE @expiryDate DATETIME2
			DECLARE @userStatus varchar(15)

			set @errorCode=0
			set @errorMessage=null
			set @currTime=GETUTCDATE()
			select @expiryDate=dateadd(minute, 15, @currTime)

			SELECT @StatusName=ST.StatusName, @createdDate=S.CreatedDate FROM SESSION S
			JOIN STATUS AS ST ON ST.StatusID=S.StatusId
			WHERE S.SESSIONID = @SessionId AND S.UserId=@UserId

			IF((@StatusName='ACTIVE') AND (@currTime <@createdDate OR @currTime>@expiryDate))
				BEGIN
					--SESSION IS EXPIRED, UPDATE IT TO INACTIVE
					set @StatusName='INACTIVE'
					UPDATE ESEVA.DBO.SESSION
					SET StatusId=2
					WHERE SessionID=@SessionId
				END
			COMMIT TRAN	
	END TRY
	BEGIN CATCH
		 set @errorCode = ERROR_NUMBER()
		 set @errorMessage = ERROR_MESSAGE()
		 ROLLBACK TRAN
	END CATCH
GO


IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[NEW_SERVICE_REQUEST]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[NEW_SERVICE_REQUEST]
END
GO

CREATE PROCEDURE NEW_SERVICE_REQUEST
@RequestedBy varchar(20),
@RequestedFor varchar(20),
@ServiceId int,
@LanguageId int,
@ServiceRequestDescription nvarchar(4000),
@Document varbinary(max),
@DocumentId varchar(50) OUTPUT,
@ServiceRequestId varchar(50) OUTPUT,
@errorCode int OUTPUT,
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
	    BEGIN TRAN

			DECLARE @StatusId int
			DECLARE @currTime DATETIME2
			
			set @errorCode=0
			set @errorMessage=null
			set @currTime=GETUTCDATE()
			set @StatusId=3
			set @DocumentId=NEWID()

			INSERT INTO ESEVA.DBO.DOCUMENT
			(DocumentId, DocumentInfo, ParentDocumentId)
			VALUES
			(@DocumentId, @Document, @DocumentId)

			set @ServiceRequestId=NEWID()

			INSERT INTO ESEVA.DBO.SERVICEREQUEST
			(ServiceRequestId,UpdatedBy,RequestedBy,RequestedFor,ServiceId, StatusId,DocumentId,LanguageId,ServiceRequestDescription,CreatedDate,ModifiedDate)
			VALUES
			(@ServiceRequestId,@RequestedBy,@RequestedBy,@RequestedFor,@ServiceId,@StatusId,@DocumentId,@LanguageId,@ServiceRequestDescription,@currTime,@currTime)
		COMMIT TRAN	
	END TRY
	BEGIN CATCH
		 set @errorCode = ERROR_NUMBER()
		 set @errorMessage = ERROR_MESSAGE()
		 ROLLBACK TRAN
	END CATCH
GO


IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_SERVICE_REQUESTS_FOR_A_USER]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_SERVICE_REQUESTS_FOR_A_USER]
END
GO

CREATE PROCEDURE GET_ALL_SERVICE_REQUESTS_FOR_A_USER
@UserId varchar(20)
AS
	
	DECLARE @RoleID int	

	select @RoleId=RoleId FROM ESEVA.DBO."USER" Where UserId=@UserId

	IF(@RoleId=3)
		BEGIN
			SELECT SR.ServiceRequestId,SR.UpdatedBy,SR.RequestedBy,SR.RequestedFor,SC.ServiceName, S.StatusName,SR.DocumentId,L.LanguageName,SR.ServiceRequestDescription,SR.CreatedDate,SR.ModifiedDate 
			FROM SERVICEREQUEST AS SR 
			JOIN STATUS AS S ON S.StatusID=SR.StatusId 
			JOIN LANGUAGE AS L ON L.LanguageID=SR.LanguageId
			JOIN SERVICE AS SC On SR.ServiceId=SC.ServiceId
			ORDER BY CreatedDate DESC
		END
	ELSE
		BEGIN
			SELECT SR.ServiceRequestId,SR.UpdatedBy,SR.RequestedBy,SR.RequestedFor,SC.ServiceName, S.StatusName,SR.DocumentId,L.LanguageName,SR.ServiceRequestDescription,SR.CreatedDate,SR.ModifiedDate 
			FROM SERVICEREQUEST AS SR 
			JOIN STATUS AS S ON S.StatusID=SR.StatusId 
			JOIN LANGUAGE AS L ON L.LanguageID=SR.LanguageId
			JOIN SERVICE AS SC On SR.ServiceId=SC.ServiceId
			WHERE RequestedBy=@UserId OR RequestedFor=@UserId 
			ORDER BY CreatedDate DESC
		END
GO


IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[UPDATE_SERVICE_REQUEST_STATUS]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[UPDATE_SERVICE_REQUEST_STATUS]
END
GO

CREATE PROCEDURE UPDATE_SERVICE_REQUEST_STATUS
@ServiceRequestId varchar(50),
@StatusId int,
@errorCode int OUTPUT,
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
		BEGIN TRAN
		DECLARE @svcReqId varchar(50)
		set @errorCode=0
		set @errorMessage=NULL
		SELECT @svcReqId=ServiceRequestId FROM SERVICEREQUEST WHERE ServiceRequestId=@ServiceRequestId

		IF(@svcReqId=@ServiceRequestId)
			BEGIN
				UPDATE SERVICEREQUEST
				set StatusId=@StatusId
				WHERE ServiceRequestId=@ServiceRequestId
			END	
			
		ELSE
			BEGIN
				set @errorCode=1
				set @errorMessage='SERVICE REQUESTID NOT FOUND'
			END
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		set @errorCode = ERROR_NUMBER()
		set @errorMessage = ERROR_MESSAGE()
		ROLLBACK TRAN
	END CATCH
GO


IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[UPDATE_USER_STATUS]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[UPDATE_USER_STATUS]
END
GO

CREATE PROCEDURE UPDATE_USER_STATUS
@UserId varchar(20),
@StatusId int,
@errorCode int OUTPUT,
@errorMessage nvarchar(4000) OUTPUT
AS
	BEGIN TRY
		BEGIN TRAN
		DECLARE @usId varchar(20)
		set @errorCode=0
		set @errorMessage=NULL
		SELECT @usId=UserId FROM ESEVA.DBO."USER" WHERE UserId=@UserId

		IF(@usId=@UserId)
			
			BEGIN
				UPDATE ESEVA.DBO."USER"
				set StatusId=@StatusId
				WHERE UserId=@UserId
			END	
		ELSE
		BEGIN
				set @errorCode=1
				set @errorMessage='USERID NOT FOUND'
		END
		COMMIT TRAN	
	END TRY
	BEGIN CATCH
		set @errorCode = ERROR_NUMBER()
		set @errorMessage = ERROR_MESSAGE()
		ROLLBACK TRAN
	END CATCH
GO