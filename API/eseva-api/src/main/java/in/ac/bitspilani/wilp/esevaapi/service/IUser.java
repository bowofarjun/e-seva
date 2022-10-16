package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;

import java.io.IOException;
import java.sql.SQLException;

public interface IUser {
    RegistrationResponse addNewUser(RegistrationRequest registrationRequest) throws SQLException, IOException;
}
