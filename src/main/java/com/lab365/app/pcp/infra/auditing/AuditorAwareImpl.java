package com.lab365.app.pcp.infra.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authName.equals("anonymousUser") ? Optional.empty() : Optional.of(Long.valueOf(authName));
    }
}
