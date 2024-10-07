    package org.bank.branch.attish.security;

    import com.nimbusds.jose.jwk.JWK;
    import com.nimbusds.jose.jwk.JWKSet;
    import com.nimbusds.jose.jwk.RSAKey;
    import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
    import com.nimbusds.jose.jwk.source.JWKSource;
    import com.nimbusds.jose.proc.SecurityContext;
    import lombok.RequiredArgsConstructor;
    import org.bank.branch.attish.security.jwt.KeyProperties;
    import org.bank.branch.attish.security.user.BankUserDetailsService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.oauth2.jwt.JwtDecoder;
    import org.springframework.security.oauth2.jwt.JwtEncoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
    import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
    import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.List;

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {
        private final KeyProperties rsaKeyProperties;
        private final BankUserDetailsService userDetailsService;

        @Bean
        JwtEncoder jwtEncoder() {
            JWK jwk =
                    new RSAKey.Builder(rsaKeyProperties.publicKey())
                            .privateKey(rsaKeyProperties.privateKey())
                            .build();
            JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
            return new NimbusJwtEncoder(jwks);
        }

        @Bean
        JwtDecoder jwtDecoder() {
            return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .cors(Customizer.withDefaults())
                    .headers(httpSecurityHeadersConfigurer -> {
                        httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                    })

                    .authorizeHttpRequests(
                            (requests) ->
                                    requests
                                            .requestMatchers("/login", "/register", "/", "/h2-console/**")
                                            .permitAll()
                                            .requestMatchers("/bank-user/*")
                                            .authenticated()
                                            .anyRequest()
                                            .authenticated())
                    .httpBasic(Customizer.withDefaults())
                    .oauth2ResourceServer(
                            oauth2 -> {
                                oauth2.jwt(Customizer.withDefaults());
                            })
                    .exceptionHandling(
                            ex -> {
                                ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
                                ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
                            })
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .userDetailsService(userDetailsService);
            return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(
                    List.of("https://localhost:8080", "https://attish-bank-site.vercel.app", "http://localhost:5173"));
            configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
