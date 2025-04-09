package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.Address;
import com.lab365.app.pcp.datasource.repository.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends GenericService<Address> {
    public AddressService(IGenericRepository<Address> repository){super(repository);}
}
