package bank.managment.backend.initilizer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import bank.managment.backend.constants.FonctionalitiesTypes;
import bank.managment.backend.entities.Permission;
import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IPermissionService;
import bank.managment.backend.services.IRoleService;

@Order(1)
@Component
public class RoleInitializer implements CommandLineRunner {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPermissionService permissionService;

	@Override
	public void run(String... args) throws Exception {
		if(!roleService.findByCode("ADMIN").isPresent()) {
			Role role1 = new Role();
			role1.setCode("ADMIN");
			role1.setLabel("Bank Administrator");
			
			Set<Permission> permissions = Arrays.asList(FonctionalitiesTypes.values())
					.stream().map(fonctionality -> {
						Permission permission = new Permission();
						permission.setCode(fonctionality.name());
						permission.setLabel(fonctionality.getLabel());
						return permission;
					})
					.collect(Collectors.toSet());
			
			role1.setPermissions(permissionService.saveAll(permissions));
			
			roleService.save(role1);
		}
	}

}
