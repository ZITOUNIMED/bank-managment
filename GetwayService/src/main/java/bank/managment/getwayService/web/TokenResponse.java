package bank.managment.getwayService.web;

import java.io.Serializable;

public class TokenResponse implements Serializable {
	private static final long serialVersionUID = 6057128929401255223L;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
