package com.cyrilselyanin.bustvm.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        authManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .cors().configurationSource(request -> {
                    var corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(
                            List.of(
                                    "http://localhost:4200",
                                    "http://192.168.56.1:4200",
                                    "http://192.168.56.104:8180"
                            )
                    );
                    corsConfig.setAllowedMethods(
                            List.of(
                                    "GET",
                                    "POST",
                                    "PUT",
                                    "PATCH",
                                    "DELETE",
                                    "OPTIONS"
                            )
                    );
                    corsConfig.setAllowedHeaders(List.of("*"));
                    return corsConfig;
                }).and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().fullyAuthenticated();
    }
}
