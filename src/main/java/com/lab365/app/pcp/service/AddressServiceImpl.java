package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Address;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import com.lab365.app.pcp.service.interfaces.IAddressService;

import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends GenericServiceImpl<Address> implements IAddressService {
    public AddressServiceImpl(IGenericRepository<Address> repository) {
        super(repository);
    }
}
