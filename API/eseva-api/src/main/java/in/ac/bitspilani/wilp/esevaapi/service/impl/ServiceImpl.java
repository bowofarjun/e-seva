package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.Service;
import in.ac.bitspilani.wilp.esevaapi.repository.ServiceRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements IService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.GET_ALL_SERVICES();
    }
}
