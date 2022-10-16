package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.Role;
import in.ac.bitspilani.wilp.esevaapi.repository.RoleRepository;
import in.ac.bitspilani.wilp.esevaapi.service.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImpl implements IRole {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.GET_ALL_ROLES();
    }
}
