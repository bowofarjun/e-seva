USE ESEVA
GO

IF EXISTS ( SELECT * 
            FROM   sysobjects 
            WHERE  id = object_id(N'[dbo].[GET_ALL_STATUS]') 
                   and OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN
    DROP PROCEDURE [dbo].[GET_ALL_STATUS]
END
GO

CREATE PROCEDURE [dbo].[GET_ALL_STATUS]
AS
	SELECT StatusId, StatusName FROM ESEVA.dbo.STATUS
GO
