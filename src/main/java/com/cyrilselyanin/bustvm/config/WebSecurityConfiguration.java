package com.cyrilselyanin.bustvm.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
public class WebSecurityConfiguration {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().configurationSource(request -> {
//                    var corsConfig = new CorsConfiguration();
//                    corsConfig.setAllowedOrigins(
//                            List.of(
//                                    "http://localhost:4200",
//                                    "http://192.168.56.1:4200",
//                                    "http://192.168.56.104:8180"
//                            )
//                    );
//                    corsConfig.setAllowedMethods(
//                            List.of(
//                                    "GET",
//                                    "POST",
//                                    "PUT",
//                                    "PATCH",
//                                    "DELETE",
//                                    "OPTIONS"
//                            )
//                    );
//                    corsConfig.setAllowedHeaders(List.of("*"));
//                    return corsConfig;
//                })
//                .and()
//                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//                .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
//                        .jwt(jwtConfigurer -> jwtConfigurer
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                );
//    }

    // configuring HttpSecurity in a Spring Security 5.4 new style
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                request -> {
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
                }
            ))
            .authorizeRequests(authorizeRequests -> authorizeRequests
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
                .jwt(jwtConfigurer -> jwtConfigurer
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );
        return http.build();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter delegate = new JwtGrantedAuthoritiesConverter();

        return new Converter<>() {
            @Override
            public Collection<GrantedAuthority> convert(Jwt jwt) {
                Collection<GrantedAuthority> grantedAuthorities = delegate.convert(jwt);

                if (jwt.getClaim("realm_access") == null) {
                    return grantedAuthorities;
                }

                JSONObject realmAccess = jwt.getClaim("realm_access");
                if (realmAccess.get("roles") == null) {
                    return grantedAuthorities;
                }
                JSONArray roles = (JSONArray) realmAccess.get("roles");

                final List<SimpleGrantedAuthority> keycloakAuthorities = roles
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
                grantedAuthorities.addAll(keycloakAuthorities);

                return grantedAuthorities;
            }
        };
    }
}
