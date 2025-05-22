package com.lab365.app.pcp.service;

import lombok.RequiredArgsConstructor;

import java.time.Instant;

import com.lab365.app.pcp.controller.dto.request.LoginRequest;
import com.lab365.app.pcp.controller.dto.response.LoginResponseDTO;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.repository.UserRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.exception.UnauthorizedException;
import com.lab365.app.pcp.service.interfaces.ITokenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements ITokenService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    @Value("${spring.application.name}")
    private String appName;
    private static final long EXPIRATION_TIME = 36000L;

    public LoginResponseDTO getToken(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUser(), request.getUser())
                .orElseThrow(() -> new InvalidException("Usuário NÃO ENCONTRADO!"));

        validatePassword(user, request.getPassword());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(generateToken(user));
        response.setExpiration(EXPIRATION_TIME);
        return response;
    }

    private void validatePassword(User user, String password) {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword()))
            throw new UnauthorizedException("Senha Incorreta!");
    }

    private String generateToken(User user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(appName)
                .issuedAt(now)
                .claim("scope", user.getRole().getAuthority())
                .expiresAt(now.plusSeconds(EXPIRATION_TIME))
                .subject(user.getId().toString())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
