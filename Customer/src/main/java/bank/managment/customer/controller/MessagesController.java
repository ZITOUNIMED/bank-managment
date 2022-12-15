package bank.managment.customer.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

	@GetMapping("/messages")
	public Map<String, String> getMessages(){
		Map<String, String> map = new HashMap<>();
		map.put("key1", "Message 1");
		map.put("key2", "Message 2");
		return map;
	}
	
	@GetMapping("/public/messages")
	public Map<String, String> getPublicMessages(){
		Map<String, String> map = new HashMap<>();
		map.put("key1", "Public Message 1");
		map.put("key2", "Public Message 2");
		return map;
	}
}
