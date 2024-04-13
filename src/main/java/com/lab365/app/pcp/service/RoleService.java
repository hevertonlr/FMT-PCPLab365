package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role> {
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
