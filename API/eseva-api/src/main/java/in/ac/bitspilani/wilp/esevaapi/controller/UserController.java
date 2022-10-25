package in.ac.bitspilani.wilp.esevaapi.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.sqlserver.jdbc.StringUtils;
import in.ac.bitspilani.wilp.esevaapi.model.LoginRequest;
import in.ac.bitspilani.wilp.esevaapi.model.LoginResponse;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationRequest;
import in.ac.bitspilani.wilp.esevaapi.model.RegistrationResponse;
import in.ac.bitspilani.wilp.esevaapi.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUser user;

    @PostMapping(path = "/sign-up",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity addNewUser(@ModelAttribute RegistrationRequest requestBody) {
        RegistrationResponse response = null;

        response = user.addNewUser(requestBody);

        return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatusCode()));
    }

    @PostMapping(path = "/sign-in",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity validateUserLogin(@RequestBody LoginRequest requestBody) {
        LoginResponse response = null;

        response = user.validateUserLogin(requestBody);

        HttpHeaders responseHeaders = new HttpHeaders();

        if(!StringUtils.isEmpty(response.getAccessToken()))
        {
            responseHeaders.add("Set-Cookie","Authorization="+response.getAccessToken()+";Max-age=900;Path=/;HttpOnly;");
        }

        return new ResponseEntity(response, responseHeaders, HttpStatus.valueOf(response.getHttpStatusCode()));
    }

    @PreAuthorize("hasRole('ROLE_CITIZEN')")
    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity testAuthentication()
    {
        //This is a test api to check if JWT authentication is working
        return new ResponseEntity("{\n" +
                "    \"OUTPUT\":\"TEST OK\"\n" +
                "}",HttpStatus.OK);
    }
}
