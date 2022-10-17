package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;
import in.ac.bitspilani.wilp.esevaapi.repository.UserRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserImpl implements IUser {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

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
                                                                passwordEncoder.encode(registrationRequest.getPassword()),
                                                                blob);


        if(Integer.parseInt(map.get("errorCode").toString())==0 && map.get("errorMessage")==null)
        {
            registrationResponse.setDocumentId(map.get("DocumentId").toString());
            registrationResponse.setStatusName(map.get("StatusName").toString());
            registrationResponse.setHttpStatusCode(200);
        }
        else
        {
            registrationResponse.setErrorCode(Integer.parseInt(map.get("errorCode").toString()));
            registrationResponse.setErrorMessage(map.get("errorMessage").toString());
            registrationResponse.setHttpStatusCode(500);
        }
        return registrationResponse;
    }

    private Blob convertToBlobFromMultiPart(MultipartFile multipartFile) throws SQLException, IOException {
        byte[] bytes = multipartFile.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        return blob;
    }
}
