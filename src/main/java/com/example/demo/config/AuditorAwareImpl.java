package com.example.demo.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.config.security.UserAuth;

public class AuditorAwareImpl implements AuditorAware<Long> {


    private Long defaultAuditorId;

    @Override
    public Optional<Long> getCurrentAuditor() {
        if (defaultAuditorId != null) {
            return Optional.of(defaultAuditorId);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserAuth userPrincipal = (UserAuth) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }

    public void setDefaultAuditorId(Long defaultAuditorId) {
        this.defaultAuditorId = defaultAuditorId;
    }
}