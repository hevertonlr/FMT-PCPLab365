package com.lab365.app.pcp.infra.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authName.equals("anonymousUser") ? Optional.empty() : Optional.of(Long.valueOf(authName));

    }
}
