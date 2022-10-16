package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;

public interface IUser {
    RegistrationResponse addNewUser(RegistrationRequest registrationRequest);
}
