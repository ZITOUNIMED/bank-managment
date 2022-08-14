package bank.managment.backend.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bank.managment.backend.entities.Credentials;
import bank.managment.backend.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private boolean accountNonExpired;
    private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	public UserDetailsImpl(User user, Credentials credentials) {
		this.username = user.getLogin();
		this.password = credentials.getPassword();
		this.enabled = credentials.isEnabled();
		this.accountNonExpired = credentials.isAccountNonExpired();
		this.credentialsNonExpired = credentials.isCredentialsNonExpired();
		this.accountNonLocked = credentials.isAccountNonLocked();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
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
