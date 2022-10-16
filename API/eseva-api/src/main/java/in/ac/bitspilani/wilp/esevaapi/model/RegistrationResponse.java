package in.ac.bitspilani.wilp.esevaapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegistrationResponse extends ErrorResponse {
    @Getter @Setter
    private String statusName;
    @Getter @Setter
    private String documentId;
}
