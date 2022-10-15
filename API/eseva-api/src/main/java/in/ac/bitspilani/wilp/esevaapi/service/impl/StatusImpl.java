package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.Status;
import in.ac.bitspilani.wilp.esevaapi.repository.StatusRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusImpl implements IStatus {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.GET_ALL_STATUS();
    }
}
