package com.ats.service.impl;

import com.ats.entity.Role;
import com.ats.repository.RoleRepository;
import com.ats.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    ModelMapper modelMapper;
    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    @Override
    public Role findRoleById(int roleId) {
        LOGGER.info("Begin findRoleById in RoleService with Role id : " + roleId);
        Role role = new Role();
        modelMapper = new ModelMapper();
        try {
             role = roleRepository.getOne(roleId);

        } catch (Exception e) {
            LOGGER.info(e);
        }
        LOGGER.info("End findRoleById in RoleService with Role id : " + roleId);
        return role;
    }

}
