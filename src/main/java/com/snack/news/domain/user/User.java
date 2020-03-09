package com.snack.news.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snack.news.domain.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
public class User extends BaseTimeEntity implements UserDetails {
	@JsonIgnore
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "USERNAME", length = 50, unique = true)
	private String username;

	@JsonIgnore
	@NotNull
	@Size(min = 4, max = 100)
	@Column(name = "PASSWORD", length = 100)
	private String password;

	@NotNull
	@Size(min = 4, max = 50)
	@Column(name = "EMAIL", length = 50)
	private String email;

	@JsonIgnore
	@Column(name = "ACTIVATED")
	@NotNull
	private boolean activated;

	@ManyToMany
	@JoinTable(
			name = "USER_AUTHORITY",
			joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "NAME")})
	@BatchSize(size = 20)
	private Set<Authority> authorities = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id.equals(user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}