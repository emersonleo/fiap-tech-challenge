package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence")
@EnableJpaRepositories("br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence")
public class JpaConfig {}
