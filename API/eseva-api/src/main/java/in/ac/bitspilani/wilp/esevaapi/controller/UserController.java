package in.ac.bitspilani.wilp.esevaapi.controller;

import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;
import in.ac.bitspilani.wilp.esevaapi.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUser user;

    @PostMapping
    @Transactional
    public ResponseEntity addNewUser(@ModelAttribute RegistrationRequest requestBody)
    {
        RegistrationResponse response = null;
        try {
            response = user.addNewUser(requestBody);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatusCode()));
    }
}
