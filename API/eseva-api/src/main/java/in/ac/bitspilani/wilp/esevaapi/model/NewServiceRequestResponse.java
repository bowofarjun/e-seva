package in.ac.bitspilani.wilp.esevaapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewServiceRequestResponse extends ErrorResponse {
    @Getter @Setter
    private String documentId;
    @Getter @Setter
    private String serviceRequestId;
}
