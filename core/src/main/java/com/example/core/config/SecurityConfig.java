package com.example.core.config;

import com.example.core.handler.CustomAccessDeniedHandler;
import com.example.core.jwt.JWTFilter;
import com.example.core.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 파일명: SecurityConfig.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-11  ksj         신규 생성
 * </pre>
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JWTUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // cors 설정
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfiguration()));

        // csrf 비활성화
        http.csrf(AbstractHttpConfigurer ->  AbstractHttpConfigurer.disable());

        //h2
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        // jwt filter
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // formLogin
        http.formLogin((login) -> login
                .loginProcessingUrl("/login")
                .loginPage("/index")
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/main")
                /*.successHandler()
                .failureHandler()*/
        );

        // logout
        http.logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutUrl("/index")
                /*.logoutSuccessHandler()
                .deleteCookies()*/
        );

        // Authorize
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/v1/**").hasAnyRole("ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
        );

        // Session 비활성화
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        );

        http.exceptionHandling(httpSecurityExceptionHandlingConfiguer -> httpSecurityExceptionHandlingConfiguer
                .accessDeniedHandler(customAccessDeniedHandler)
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));        // front 서버 주소 나오면 수정해야함
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));        // 알맞게 수정해야함
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));        // 알맞게 수정해야함
        configuration.setExposedHeaders(Arrays.asList("set-cookie"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
