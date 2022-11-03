package bank.managment.getwayService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetwayRestController {

	@GetMapping("/secure/data")
	public Map<String, String> getSecureData(){
		Map<String, String> map = new HashMap<>();
		map.put("secure1", "Value 1");
		map.put("secure2", "Value 2");
		return map;
	}
	
	@GetMapping("/public/data")
	public Map<String, String> getPublicData(){
		Map<String, String> map = new HashMap<>();
		map.put("public1", "Value 1");
		map.put("public2", "Value 2");
		return map;
	}
}
