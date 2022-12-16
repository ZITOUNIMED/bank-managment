package bank.managment.getwayService.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class DefaultController {
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody LoginRequest loginRequest){
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = getLoginPageData();
		String cookie = authLogin(loginRequest.getUsername(), loginRequest.getPassword(), data.get("_csrf"), data.get("cookie"));
		result.put("token", cookie);
		return result;
	}
	
	private String authLogin(String username, String password, String _csrf, String cookie) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://auth-server:9000/login";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", cookie);
		headers.add("Accept", "*/*");
		
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		requestBody.add("username", username);
		requestBody.add("password", password);
		requestBody.add("_csrf", _csrf);
		
		HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
		
		ResponseEntity<?> responseLogin = restTemplate
				.exchange(url, HttpMethod.POST, formEntity, Object.class);
		return getCookie(responseLogin.getHeaders());
	}
	
	private Map<String, String> getLoginPageData(){
		Map<String, String> result = new HashMap<>();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://auth-server:9000/login";
		ResponseEntity<String> response= restTemplate.getForEntity(url, String.class);
		int from = response.getBody().indexOf("value=\"");
		int to = response.getBody().indexOf("\"", from + 10);
		result.put("_csrf", response.getBody().substring(from + 7, to));
		result.put("cookie", getCookie(response.getHeaders()));
		return result;
	}
	
	private String getCookie(HttpHeaders headers) {
		List<String> cookies = headers.get("Set-Cookie");
		if(cookies != null && cookies.size()>0) {
			return cookies.get(0);
		}
		return "";
	}
}
