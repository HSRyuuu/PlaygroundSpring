package com.hsryuuu.base.application.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class JpaConfig implements AuditorAware<UUID> {

    @Override
    @NonNull
    public Optional<UUID> getCurrentAuditor() {
        return Optional.of(UUID.randomUUID());
    }
}
