package bank.managment.backend.initilizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IRoleService;

@Order(1)
@Component
public class RoleInitializer implements CommandLineRunner {

	@Autowired
	private IRoleService roleService;
	@Override
	public void run(String... args) throws Exception {
		if(!roleService.findByCode("ADMIN").isPresent()) {
			Role role1 = new Role();
			role1.setCode("ADMIN");
			role1.setLabel("Bank Administrator");
			roleService.save(role1);
		}
	}

}
