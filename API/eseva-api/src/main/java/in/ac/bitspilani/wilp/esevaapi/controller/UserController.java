package in.ac.bitspilani.wilp.esevaapi.controller;

import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;
import in.ac.bitspilani.wilp.esevaapi.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUser user;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity addNewUser(@ModelAttribute RegistrationRequest requestBody) {
        RegistrationResponse response = null;

        response = user.addNewUser(requestBody);

        return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatusCode()));
    }
}
