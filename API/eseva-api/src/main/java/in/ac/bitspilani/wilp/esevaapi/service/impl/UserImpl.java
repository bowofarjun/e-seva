package in.ac.bitspilani.wilp.esevaapi.service.impl;

import com.microsoft.sqlserver.jdbc.StringUtils;
import in.ac.bitspilani.wilp.esevaapi.access.EsevaJWTTokenProvider;
import in.ac.bitspilani.wilp.esevaapi.model.LoginRequest;
import in.ac.bitspilani.wilp.esevaapi.model.LoginResponse;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;
import in.ac.bitspilani.wilp.esevaapi.repository.UserRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Blob;
import java.util.Map;

import static in.ac.bitspilani.wilp.esevaapi.util.EsevaUtil.*;

@RequiredArgsConstructor
@Service
public class UserImpl implements IUser {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EsevaJWTTokenProvider eSevaJWTTokenProvider;

    @Override
    public RegistrationResponse addNewUser(RegistrationRequest registrationRequest) {

        RegistrationResponse registrationResponse = new RegistrationResponse();
        Blob blob;

        try
        {
            blob = convertToBlobFromMultiPart(registrationRequest.getDocument());
        }
        catch(Exception ex)
        {
            registrationResponse.setErrorCode(500);
            registrationResponse.setErrorMessage(ex.getMessage());
            registrationResponse.setHttpStatusCode(500);
            return registrationResponse;
        }

        Map<String,?> map = userRepository.ADD_NEW_USER(registrationRequest.getUserId(),
                                                                registrationRequest.getRoleId(),
                                                                registrationRequest.getUserName(),
                                                                registrationRequest.getLanguageId(),
                                                                registrationRequest.getPhoneNumber(),
                                                                registrationRequest.getEmailId(),
                                                                generateHash(registrationRequest.getPassword()),
                                                                blob);

        Integer errorCode = Integer.parseInt(validateNullStringInMap(map.get("errorCode")));
        String errorMessage = validateNullStringInMap(map.get("errorMessage"));

        if(errorCode==0 && errorMessage==null)
        {
            registrationResponse.setDocumentId(map.get("DocumentId").toString());
            registrationResponse.setStatusName(map.get("StatusName").toString());
            registrationResponse.setHttpStatusCode(200);
        }
        else
        {
            registrationResponse.setErrorCode(errorCode);
            registrationResponse.setErrorMessage(errorMessage);
            registrationResponse.setHttpStatusCode(500);
        }
        return registrationResponse;
    }

    @Override
    public LoginResponse validateUserLogin(LoginRequest loginRequest) {
        LoginResponse response= new LoginResponse();
        Map<String,?> map = userRepository.VALIDATE_USER_LOGIN(loginRequest.getUserId(), generateHash(loginRequest.getPassword()));

        Integer isLoginSuccessful = Integer.parseInt(validateNullStringInMap(map.get("isLoginSuccessful")));
        String userName = validateNullStringInMap(map.get("UserName"));
        Integer loginAttempts = Integer.parseInt(validateNullStringInMap(map.get("LoginAttempts")));
        String roleName = validateNullStringInMap(map.get("RoleName"));
        String sessionId = validateNullStringInMap(map.get("SessionId"));
        String statusName = validateNullStringInMap(map.get("StatusName"));
        Integer errorCode = Integer.parseInt(validateNullStringInMap(map.get("errorCode")));
        String errorMessage = validateNullStringInMap(map.get("errorMessage"));

        if(isLoginSuccessful == 1) {
            String accessToken = eSevaJWTTokenProvider.createToken(loginRequest.getUserId(), roleName, sessionId, userName);
            response.setHttpStatusCode(200);
            response.setAccessToken(accessToken);
            response.setUserName(userName);
            response.setStatus(statusName);
        }
        else
        {
            if(StringUtils.isEmpty(statusName))
            {
                //This will happen when there is no row returned for a userId
                response.setErrorCode(404);
                response.setHttpStatusCode(404);
                response.setErrorMessage("USER DOESN'T EXIST");
                response.setStatus(statusName);
            }
            else if(loginAttempts==6 && statusName.equalsIgnoreCase("ACTIVE"))
            {
                response.setErrorCode(401);
                response.setHttpStatusCode(401);
                response.setErrorMessage("ACCOUNT IS LOCKED AND WILL BE DISABLED. PLEASE CHECK WITH ADMIN");
                response.setStatus(statusName);
            }
            else if(loginAttempts<6 && statusName.equalsIgnoreCase("INACTIVE"))
            {
                response.setErrorCode(401);
                response.setHttpStatusCode(401);
                response.setErrorMessage("ACCOUNT IS DISABLED. PLEASE CHECK WITH ADMIN");
                response.setStatus(statusName);
            }
            else
            {
                response.setErrorMessage(errorMessage);
                response.setErrorCode(errorCode);
                response.setHttpStatusCode(401);
                response.setPendingLoginAttempts(5-loginAttempts);
                response.setStatus(statusName);
            }
        }
        return response;
    }
}
