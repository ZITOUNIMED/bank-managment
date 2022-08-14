package bank.managment.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.dto.LinkDto;
import bank.managment.backend.services.IMenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
	@Autowired
	private IMenuService menuService;
	
	@GetMapping
	public ResponseEntity<List<LinkDto>> getMenu(){
		return ResponseEntity.ok(menuService.getMenu());
	}
}
