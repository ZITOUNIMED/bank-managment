package bank.managment.client.clientserver.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CorsFilter corsFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	.addFilterBefore(corsFilter, SessionManagementFilter.class)
		.csrf().disable()
        .authorizeRequests(authorizeRequests ->
          authorizeRequests.anyRequest().authenticated()
        )
        .oauth2Login(oauth2Login ->
          oauth2Login.loginPage("/oauth2/authorization/bank-client-oidc"))
        .oauth2Client(withDefaults());
      return http.build();
    }
}