package com.snack.news.config;

import com.snack.news.security.SnakAccessDeniedHandler;
import com.snack.news.security.SnakAuthenticationEntryPoint;
import com.snack.news.security.jwt.JWTConfigurer;
import com.snack.news.security.jwt.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private SnakAuthenticationEntryPoint entryPoint;
	private SnakAccessDeniedHandler accessDeniedHandler;
	private JWTProvider tokenProvider;

	public SecurityConfig(SnakAuthenticationEntryPoint entryPoint, SnakAccessDeniedHandler accessDeniedHandler, JWTProvider tokenProvider) {
		this.entryPoint = entryPoint;
		this.accessDeniedHandler = accessDeniedHandler;
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.httpBasic().disable()
//				.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(entryPoint)
				.accessDeniedHandler(accessDeniedHandler)

				// enable h2-console
				.and()
				.headers()
				.frameOptions()
				.sameOrigin()

				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				.authorizeRequests()
				.antMatchers("/api/auth").permitAll()
				.anyRequest().authenticated()

				.and()
				.apply(securityConfigurerAdapter());
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
}
