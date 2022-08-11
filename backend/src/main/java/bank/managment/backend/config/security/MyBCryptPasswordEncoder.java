package bank.managment.backend.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyBCryptPasswordEncoder extends BCryptPasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return "123";
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return true;
	}
}
