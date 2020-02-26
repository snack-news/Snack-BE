package com.snack.news.repository;

import com.snack.news.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String login);

	Optional<User> findOneWithAuthoritiesByUsername(String lowercaseLogin);
}
