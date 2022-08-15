package bank.managment.backend.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bank.managment.backend.entities.Credentials;
import bank.managment.backend.entities.Role;
import bank.managment.backend.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private boolean accountNonExpired;
    private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	List<GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user, Credentials credentials) {
		this.username = user.getLogin();
		this.password = credentials.getPassword();
		this.enabled = credentials.isEnabled();
		this.accountNonExpired = credentials.isAccountNonExpired();
		this.credentialsNonExpired = credentials.isCredentialsNonExpired();
		this.accountNonLocked = credentials.isAccountNonLocked();
		GrantedAuthority authority = () -> user.getRole().getCode();
		authorities = Arrays.asList(authority);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	

}
