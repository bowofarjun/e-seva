package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.LoginRequest;
import in.ac.bitspilani.wilp.esevaapi.model.LoginResponse;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;

public interface IUser {
    RegistrationResponse addNewUser(RegistrationRequest registrationRequest);
    LoginResponse validateUserLogin(LoginRequest loginRequest);
}
