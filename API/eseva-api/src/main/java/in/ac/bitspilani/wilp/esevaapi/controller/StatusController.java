package in.ac.bitspilani.wilp.esevaapi.controller;

import in.ac.bitspilani.wilp.esevaapi.service.IStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    IStatus status;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly=true)
    public ResponseEntity getAllStatus()
    {
        return new ResponseEntity<>(status.getAllStatuses(), HttpStatus.OK);
    }
}
