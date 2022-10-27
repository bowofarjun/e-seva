package in.ac.bitspilani.wilp.esevaapi.controller;

import in.ac.bitspilani.wilp.esevaapi.access.EsevaJWTTokenProvider;
import in.ac.bitspilani.wilp.esevaapi.model.NewServiceRequestRequest;
import in.ac.bitspilani.wilp.esevaapi.model.NewServiceRequestResponse;
import in.ac.bitspilani.wilp.esevaapi.service.IServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/service-request")
public class ServiceRequestController {

    @Autowired
    IServiceRequest serviceRequest;

    @Autowired
    EsevaJWTTokenProvider esevaJWTTokenProvider;

    @PreAuthorize("hasAnyRole('ROLE_CITIZEN','ROLE_VENDOR','ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity getAllServiceRequestsByUser(HttpServletRequest httpServletRequest)
    {
        String token = esevaJWTTokenProvider.resolveToken(httpServletRequest);
        String userId = esevaJWTTokenProvider.getUserId(token);
        return new ResponseEntity(serviceRequest.getAllServiceRequestsForAUser(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_CITIZEN','ROLE_VENDOR','ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity newServiceRequest(@ModelAttribute NewServiceRequestRequest newServiceRequestRequest, HttpServletRequest httpServletRequest)
    {
        String token = esevaJWTTokenProvider.resolveToken(httpServletRequest);
        String userId = esevaJWTTokenProvider.getUserId(token);

        NewServiceRequestResponse newServiceRequestResponse = serviceRequest.newServiceRequest(userId, newServiceRequestRequest);
        return new ResponseEntity(newServiceRequestResponse, HttpStatus.valueOf(newServiceRequestResponse.getHttpStatusCode()));
    }
}
