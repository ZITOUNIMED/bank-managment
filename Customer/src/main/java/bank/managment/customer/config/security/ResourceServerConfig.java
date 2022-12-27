package bank.managment.customer.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;

@EnableWebSecurity
public class ResourceServerConfig {
	@Autowired
	private CorsFilter corsFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	.addFilterBefore(corsFilter, SessionManagementFilter.class)
		.csrf().disable()
        .authorizeRequests(authz -> authz.antMatchers(HttpMethod.GET, "/public/**")
            .permitAll()
            .anyRequest()
            .authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}