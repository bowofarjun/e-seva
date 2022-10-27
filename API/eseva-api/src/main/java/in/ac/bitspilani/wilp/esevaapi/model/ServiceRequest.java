package in.ac.bitspilani.wilp.esevaapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@NamedStoredProcedureQuery(name = "NEW_SERVICE_REQUEST", procedureName = "NEW_SERVICE_REQUEST", parameters = {
        @StoredProcedureParameter(name = "RequestedBy", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "RequestedFor", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "ServiceId", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "LanguageId", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "ServiceRequestDescription", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "Document", mode = ParameterMode.IN, type = Blob.class),
        @StoredProcedureParameter(name = "DocumentId", mode=ParameterMode.OUT, type = String.class),
        @StoredProcedureParameter(name = "ServiceRequestId", mode = ParameterMode.OUT, type = String.class),
        @StoredProcedureParameter(name = "errorCode",mode = ParameterMode.OUT, type=Integer.class),
        @StoredProcedureParameter(name = "errorMessage", mode = ParameterMode.OUT, type = String.class)}
)
@NamedStoredProcedureQuery(name = "UPDATE_SERVICE_REQUEST_STATUS", procedureName = "UPDATE_SERVICE_REQUEST_STATUS", parameters = {
        @StoredProcedureParameter(name = "ServiceRequestId", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "StatusId",mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "errorCode",mode = ParameterMode.OUT, type=Integer.class),
        @StoredProcedureParameter(name = "errorMessage", mode = ParameterMode.OUT, type = String.class)
})
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceRequest extends ErrorResponse{
    @Id
    @Column
    @Getter @Setter
    private String serviceRequestId;
    @Column
    @Getter @Setter
    private String updatedBy;
    @Column
    @Getter @Setter
    private String requestedBy;
    @Column
    @Getter @Setter
    private String requestedFor;
    @Column
    @Getter @Setter
    private String serviceName;
    @Column
    @Getter @Setter
    private String statusName;
    @Column
    @Getter @Setter
    private String DocumentId;
    @Column
    @Getter @Setter
    private String languageName;
    @Column
    @Getter @Setter
    private String serviceRequestDescription;
    @Column
    @Getter @Setter
    private Date createdDate;
    @Column
    @Getter @Setter
    private Date modifiedDate;
}
