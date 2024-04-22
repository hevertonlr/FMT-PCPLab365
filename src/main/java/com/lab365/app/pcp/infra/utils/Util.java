package com.lab365.app.pcp.infra.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Util {
    public static String toJSON(Object obj) {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathMethod() {
        return (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
    }

    public static Jwt getTokenJwt() {
        return (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getCredentials();
    }

    public static String getTokenFromRequest() {
        return getTokenJwt().getTokenValue();
    }

    public static Long getUserIdFromToken() {
        return Long.valueOf(getTokenJwt().getSubject());
    }

    public static String getRoleFromToken() {
        return getTokenJwt().getClaimAsString("scope").toUpperCase();
    }
}
