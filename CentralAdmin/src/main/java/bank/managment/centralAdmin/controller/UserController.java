package bank.managment.centralAdmin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.centralAdmin.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping
	public List<User> getUsers(){
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		user1.setFirstName("First Name 1.4");
		user1.setLastName("Last Name 1.4");
		
		user2.setFirstName("First Name 2");
		user2.setLastName("Last Name 2");
		
		user3.setFirstName("First Name 3");
		user3.setLastName("Last Name 3");
		return Arrays.asList(user1, user2, user3);
	}
}
