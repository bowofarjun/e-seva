package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

public class RegistrationResponse extends ErrorResponse {
    @Getter @Setter
    private String statusName;
    @Getter @Setter
    private String documentId;
}
