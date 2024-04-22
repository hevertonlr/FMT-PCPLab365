package com.lab365.app.pcp.service;

import com.lab365.app.pcp.controller.dto.request.LoginRequest;
import com.lab365.app.pcp.controller.dto.response.LoginResponse;
import com.lab365.app.pcp.datasource.entity.User;
import com.lab365.app.pcp.datasource.repository.UserRepository;
import com.lab365.app.pcp.infra.exception.InvalidException;
import com.lab365.app.pcp.infra.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    @Value("${spring.application.name}")
    private String appName;
    private static final long EXPIRATION_TIME = 36000L;

    public LoginResponse getToken(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new InvalidException("Usuário NÃO ENCONTRADO!"));

        validatePassword(user, request.password());

        return new LoginResponse(generateToken(user), EXPIRATION_TIME);
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
