package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@NamedStoredProcedureQuery(name="ADD_NEW_USER", procedureName = "ADD_NEW_USER", parameters = {
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "UserId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "RoleId", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "UserName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "LanguageId", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "PhoneNumber", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "EmailId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "Password", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "Document", type = Blob.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "StatusName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "DocumentId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorCode", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorMessage", type = String.class),
})
@NamedStoredProcedureQuery(name="VALIDATE_USER_LOGIN", procedureName = "VALIDATE_USER_LOGIN", parameters = {
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "UserId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "Password", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "isLoginSuccessful", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "UserName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "LoginAttempts", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "RoleName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "SessionId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "StatusName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorCode", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorMessage", type = String.class),
})
public class User {
    @Id
    @Column
    @Getter @Setter
    private String userId;

    @Column
    @Getter @Setter
    private Integer roleId;

    @Column
    @Getter @Setter
    private String userName;

    @Column
    @Getter @Setter
    private String documentId;

    @Column
    @Getter @Setter
    private Integer statusId;

    @Column
    @Getter @Setter
    private Integer languageId;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Getter @Setter
    private String emailId;

    @Column
    @Getter @Setter
    private String password;

    @Column
    @Getter @Setter
    private Date createdDate;

    @Column
    @Getter @Setter
    private Date modifiedDate;

    @Column
    @Getter @Setter
    private String updatedBy;

    @Column
    @Getter @Setter
    private Integer loginAttempts;

    @Column
    @Getter @Setter
    private String statusName;

    @Column
    @Getter @Setter
    @Lob
    private Blob document;
}
