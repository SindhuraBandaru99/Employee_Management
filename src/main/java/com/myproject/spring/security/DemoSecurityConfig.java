package com.myproject.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class DemoSecurityConfig {
	
	//We write the JDBC Code to get users and authorities
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		//Query to retrieve user by username
		
		jdbcUserDetailsManager.setUsersByUsernameQuery("Select user_id,pw,active from members where user_id =?");
		//Query to retrive role by username
		
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("Select user_id,role from roles where user_id=?");
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers(HttpMethod.GET, "/user/employees").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.GET, "/user/employees/**").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.POST, "/user/employees").hasRole("MANAGER")
			.requestMatchers(HttpMethod.PUT, "/user/employees").hasRole("MANAGER")
			.requestMatchers(HttpMethod.DELETE, "/user/employees/**").hasRole("ADMIN")	
		);
		
		//Basic Authentication
		http.httpBasic(Customizer.withDefaults());
		
		//Disable Cross Site Request Forgery(CSRF)
		//Not Required for Stateless APIs that use POST, PUT, DELETE
		http.csrf(csrf -> csrf.disable());

	return http.build();
		
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails  sindhura = User.builder()
//				.username("sindhura")
//				.password("{noop}test123")
//				.roles("EMPLOYEE")
//				.build();
//		UserDetails  varsha = User.builder()
//				.username("varsha")
//				.password("{noop}test123")
//				.roles("EMPLOYEE", "MANAGER")
//				.build();
//		
//		UserDetails  sahithi = User.builder()
//				.username("sahithi")
//				.password("{noop}test123")
//				.roles("MANAGER")
//				.build();
//		
//		UserDetails  devika = User.builder()
//				.username("devika")
//				.password("{noop}test123")
//				.roles("EMPLOYEE", "MANAGER", "ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(sindhura, varsha, sahithi, devika) ;
//		
//	}

}
