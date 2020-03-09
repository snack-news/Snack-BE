package com.snack.news.service;

import com.snack.news.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Slf4j
@Component("userDetailsService")
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating user '{}'", login);

		if (new EmailValidator().isValid(login, null)) {
			return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login).orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
		}

		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		return userRepository.findOneWithAuthoritiesByUsername(lowercaseLogin).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
	}
}
