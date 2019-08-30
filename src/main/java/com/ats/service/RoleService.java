package com.ats.service;

import com.ats.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService{
	Role findRoleById(int roleId);
}
