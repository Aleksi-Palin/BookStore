package fi.haagahelia.bookstorepalinaleksi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration 
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

 	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean 
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests( authorize -> authorize        // / and /home paths are configured to not
				.requestMatchers("/", "/home","/css/home.css","/css/bootstrap.min.css").permitAll()    // require any authentication.
				.anyRequest().authenticated()                 // All other paths must be authenticated.
			)                                             // If you don't give loginPage
		.formLogin( formlogin -> formlogin                    // your application will use the
			.loginPage("/login")                          // spring boot default login page.
			.defaultSuccessUrl("/booklist", true)      // <-- Tells where to go after
			.permitAll()                                  // successful login.
		)
		.logout( logout -> logout
			.permitAll()
		);
		return http.build();
	}

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        String userPassword = generatePassword();
        String adminPassword = generatePassword();

        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode(userPassword))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode(adminPassword))
            .roles("ADMIN")
            .build();

        logger.warn("Generated login credentials - user: {}, admin: {}", userPassword, adminPassword);

        List<UserDetails> users = new ArrayList<>();
        users.add(user);
        users.add(admin);
        return new InMemoryUserDetailsManager(users);
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}