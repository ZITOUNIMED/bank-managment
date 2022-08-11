package bank.managment.backend.initilizer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import bank.managment.backend.entities.Role;
import bank.managment.backend.entities.User;
import bank.managment.backend.services.IRoleService;
import bank.managment.backend.services.IUserService;

@Order(2)
@Component
public class UserInitializer implements CommandLineRunner {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminOptional = userService.findByLogin("admin");
		if(!adminOptional.isPresent()) {
			Optional<Role> roleAdminOptional = roleService.findByCode("ADMIN");
			
			if(roleAdminOptional.isPresent()) {
				User admin = new User();
				admin.setRole(roleAdminOptional.get());
				admin.setEmail("admin@test.com");
				admin.setFirstName("Med");
				admin.setLastName("Zitouni");
				admin.setLogin("admin");
				userService.save(admin);
			}
		}
		
	}

}
