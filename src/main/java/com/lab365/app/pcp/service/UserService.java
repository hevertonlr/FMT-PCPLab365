package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User entity) throws RuntimeException {
         ((UserRepository) super.repository).findByUsername(entity.getUsername())
                .ifPresentOrElse((user) -> {
                    throw new RuntimeException("Usuário já existe");
                }, () -> {
                    entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
                    super.save(entity);
                });
        return entity;
    }
}
