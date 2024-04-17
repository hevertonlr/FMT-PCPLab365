package com.lab365.app.pcp.service;

import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.repository.RoleRepository;
import com.lab365.app.pcp.datasource.repository.UserRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Slf4j
@Service
public class UserService extends GenericService<User> {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       RoleRepository roleRepository) {
        super(repository);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(User entity) throws InvalidException {
        log.info("Salvando: Registrando \n{}\n", toJSON(entity));
        ((UserRepository) super.repository)
                .findByUsername(entity.getUsername())
                .ifPresentOrElse((user) -> {
                    log.warn("Salvando: Usuário já existe ({})", user.getUsername());
                    throw new InvalidException("Usuário já existe");
                }, () -> {
                    String rolename = entity.getRole().getName().toUpperCase();
                    roleRepository.findByName(rolename).ifPresentOrElse(role -> {
                        entity.setRole(role);
                        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
                        super.save(entity);
                        log.debug("Salvando: Registro criado -> \n{}\n", toJSON(entity));
                    }, () -> {
                        log.error("Salvando: ERRO -> Papel '{}' NÃO ENCONTRADO!", rolename);
                        throw new InvalidException("Erro ao cadastrar o usuário.");
                    });
                });
        return entity;
    }
}
