package bank.managment.client.clientserver.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	@GetMapping("/access-token")
    public OAuth2AccessToken getAccessToken(@RegisteredOAuth2AuthorizedClient("bank-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
    	return authorizedClient.getAccessToken();
    }
    
    @GetMapping("/refresh-token")
    public OAuth2RefreshToken getRefreshToken(@RegisteredOAuth2AuthorizedClient("bank-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
    	return authorizedClient.getRefreshToken();
    }
}
