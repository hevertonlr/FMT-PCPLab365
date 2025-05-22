package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Role;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.service.interfaces.IRoleService;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements IRoleService {
    public RoleServiceImpl(IGenericRepository<Role> repository) {
        super(repository);
    }
}
