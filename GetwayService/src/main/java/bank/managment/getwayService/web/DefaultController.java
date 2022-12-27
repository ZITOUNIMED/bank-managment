package bank.managment.getwayService.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class DefaultController {
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		Map<String, String> data = getLoginPageData();
		String cookie = authLogin(loginRequest.getUsername(), loginRequest.getPassword(), data.get("_csrf"), data.get("cookie"));
		String referer = "";
		String url = "http://127.0.0.1:8090/access-token";
		List<String> cookies = new ArrayList<>();
		cookies.add(cookie);
		return getAccessToken(cookies, url, referer);
	}
	
	private ResponseEntity<?> getAccessToken(List<String> cookies, String url, String referer) {
		WebClient client = WebClient.builder().build();
		Consumer<HttpHeaders> headersConsumer = headers -> {
			for(String cookie: cookies) {
				headers.add("cookie", cookie);
			}
			if(!StringUtils.isEmpty(referer)) {
				headers.add("referer", referer);
			}
		};
		
		
		ResponseEntity<String> response = client.get()
				.uri(url)
				.headers(headersConsumer)
			    .retrieve()
			    .onStatus(
			        status -> status.value() == 401 || status.value() == 302,
			        clientResponse -> Mono.empty()
			    )
			    .toEntity(String.class)
			    .block();

		if (response.getStatusCodeValue() == 401) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else if(response.getStatusCodeValue() == 302) {
			HttpHeaders headers = response.getHeaders();
			String location = getLocation(headers);
			String cookie2 = getCookie(headers);
			if(!StringUtils.isEmpty(cookie2)) {
				cookies.add(cookie2);
			}
			
			if(location.contains("?error")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} 
			
			location = decode(location);
			return getAccessToken(cookies, location, url);
		} else if(response.getStatusCodeValue() == 200) {
		   String responseBody = response.getBody();
		   JsonObject convertedObject = new Gson().fromJson(responseBody, JsonObject.class);
		   String token = convertedObject.get("tokenValue").getAsString();
		   TokenResponse tokenResponse = new TokenResponse();
		   tokenResponse.setToken(token);
		   return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
	
	private String decode(String value) {
	    try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return "";
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
	
	private String getLocation(HttpHeaders headers) {
		List<String> locations = headers.get("Location");
		if(locations != null && locations.size()>0) {
			return locations.get(0);
		}
		return "";
	}
}
