package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class AuditingConfig {

	@Bean
    AuditorAwareImpl auditorProvider() {
        return new AuditorAwareImpl();
    }
	
}
