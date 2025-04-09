package com.lab365.app.pcp.infra.config;

import com.lab365.app.pcp.infra.auditing.AuditorAwareImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JPAConfig {
    @Bean
    AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }
}
