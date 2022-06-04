package com.aadesh.Todolistapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
@Configuration
public class TodolistApiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApiApplication.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, @Value("${portal.cors.origins}") String[] corsOrigins) throws Exception {
		http
				.csrf().disable()
				.authorizeHttpRequests((authz) -> authz
						.anyRequest().permitAll()
				)
				.cors().configurationSource(corsConfiguration(corsOrigins));

		return http.build();
	}

	private CorsConfigurationSource corsConfiguration(String[] allowedOrigins) {
		//This can be customized as required
		CorsConfiguration configuration = new CorsConfiguration();
		List<String> allowOrigins = Arrays.asList(allowedOrigins);
		configuration.setAllowedOrigins(allowOrigins);
		configuration.setAllowedMethods(singletonList("*"));
		configuration.setAllowedHeaders(singletonList("*"));
		// in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
