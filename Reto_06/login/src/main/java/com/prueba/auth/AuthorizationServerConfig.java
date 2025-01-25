package com.prueba.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;


import java.time.Duration;
import java.util.Set;
import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient angularClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("angularapp")
                .clientSecret(passwordEncoder.encode("12345"))
                .scopes(scopes -> scopes.addAll(Set.of("read", "write")))
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(1))
                        .refreshTokenTimeToLive(Duration.ofDays(30))
                        .build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .build();

        return new InMemoryRegisteredClientRepository(angularClient);
    }
}
