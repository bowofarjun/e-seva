package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedStoredProcedureQuery(name="VALIDATE_USER_SESSION", procedureName = "VALIDATE_USER_SESSION", parameters = {
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "UserId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN,name = "SessionId", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "StatusName", type = String.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorCode", type = Integer.class),
        @StoredProcedureParameter(mode= ParameterMode.OUT,name = "errorMessage", type = String.class),
})
public class Session {
    @Id
    @Column
    @Getter @Setter
    private String sessionId;
    @Column
    @Getter @Setter
    private String userId;
    @Column
    @Getter @Setter
    private Integer statusId;
    @Column
    @Getter @Setter
    private Date  createdDate;
    @Column
    @Getter @Setter
    private Date  modifiedDate;
    @Column
    @Getter @Setter
    private Date  expiryDate;
}
