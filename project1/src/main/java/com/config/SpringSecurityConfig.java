package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	      	

	    http.cors().and().csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
	        .and()
	        .authorizeRequests()
	        .antMatchers("/h2-console/**/**").permitAll()
	        .antMatchers(HttpMethod.POST,"/user/register").permitAll()
	        .antMatchers("/user/activate").permitAll()
	        .antMatchers("/user/reset-password").permitAll()
	        .antMatchers("/user/reset-password").permitAll()
	        .antMatchers("/admin/user").hasRole("ADMIN")
	        .antMatchers("/roles").permitAll();

	    http.headers().frameOptions().disable(); // its require for h2-console

	}

//	public JwtAuthenticationFilter jwtAuthorizationFilter() throws Exception {
//	    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
//	    jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
//	    return jwtAuthenticationFilter;
//	}
	
	private PasswordEncoder getPasswordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
