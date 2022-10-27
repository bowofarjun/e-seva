package in.ac.bitspilani.wilp.esevaapi.service;

import in.ac.bitspilani.wilp.esevaapi.model.*;

public interface IUser {
    RegistrationResponse addNewUser(RegistrationRequest registrationRequest);
    LoginResponse validateUserLogin(LoginRequest loginRequest);
    UpdateUserStatusResponse updateUserStatus(UpdateUserStatusRequest updateUserStatusRequest);
}
