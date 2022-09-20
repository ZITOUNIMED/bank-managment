package bank.managment.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.entities.User;
import bank.managment.backend.services.IUserService;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('MANAGE_USERS')")
public class UsersController {
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok(userService.findAll());
	}
}
