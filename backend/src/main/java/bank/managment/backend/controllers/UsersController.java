package bank.managment.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.entities.User;
import bank.managment.backend.services.IUserService;
import bank.managment.backend.trace.Trace;

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
	
	@PostMapping
	@Trace
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/{id}")
	@Trace
	public void delete(@PathVariable Long id){
		userService.deleteById(id);
	}
}
