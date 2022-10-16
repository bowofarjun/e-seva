package in.ac.bitspilani.wilp.esevaapi.model;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse {
    @Getter @Setter
    private Integer httpStatusCode;

    @Getter @Setter
    private Integer errorCode;

    @Getter @Setter
    private String errorMessage;
}
