package org.example.todolist.config;
import org.example.todolist.security.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(final JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* http.csrf((csrf) -> csrf.disable()) */
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/test-possible-deployment/is-deployed").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // cho phep cac yeu cau voi HTTP OPTIONS truy cap khong xac thuc
                .anyRequest().authenticated())
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Allow frontend URL
        corsConfiguration.addAllowedMethod("GET"); // Allow GET method
        corsConfiguration.addAllowedMethod("POST"); // Allow POST method
        corsConfiguration.addAllowedMethod("PUT"); // Allow PUT method
        corsConfiguration.addAllowedMethod("DELETE"); // Allow DELETE method
        corsConfiguration.addAllowedMethod("OPTIONS"); // Allow OPTIONS method
        corsConfiguration.addAllowedHeader("Authorization"); // Allow Authorization header
        corsConfiguration.addAllowedHeader("Content-Type"); // Allow Content-Type header
        corsConfiguration.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Apply CORS config for all endpoints

        return source;
    }

    //to encoder password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
