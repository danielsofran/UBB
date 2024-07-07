package org.app.user.login;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig{
    private List<EndpointData> bypassingEndpoints = new ArrayList<>();

    @Bean
    public void setupBypassingEndpoints()
    {
        bypassingEndpoints.add(new EndpointData("/api/users", HttpMethod.GET));
        bypassingEndpoints.add(new EndpointData("/api/users/login", HttpMethod.POST));
        bypassingEndpoints.add(new EndpointData("/api/users/register", HttpMethod.POST));

        bypassingEndpoints.add(new EndpointData("/api/causes", HttpMethod.GET));
        bypassingEndpoints.add(new EndpointData("/api/causes/photos", HttpMethod.GET));
        bypassingEndpoints.add(new EndpointData("/api/causes/\\d+/reactions", HttpMethod.GET));
        bypassingEndpoints.add(new EndpointData("/api/causes/comments/\\d+/reactions", HttpMethod.GET));
        bypassingEndpoints.add(new EndpointData("/api/photos", HttpMethod.GET));

        //More endpoints that do not require token checking can be added here, if needed
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*"); // Allow all origins for now; consider tightening this in production
        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods
        corsConfig.addAllowedHeader("*"); // Allow all headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig); // Apply CORS configuration to /api/** paths

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        InterceptorRegistry registry = new InterceptorRegistry();
        registry.addInterceptor(new TokenValidationInterceptor());

        http
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(new TokenValidationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/**").permitAll()
                );
        return http.build();
    }

    @Autowired
    private TokenValidationInterceptor tokenValidationInterceptor;

    private class TokenValidationFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            try {
                if (tokenValidationInterceptor.preHandle(request, response, null)) {
                    filterChain.doFilter(request, response);
                } else if(bypassingEndpoints.stream().anyMatch(endpoint -> endpoint.matches(request))) {
                    filterChain.doFilter(request, response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
