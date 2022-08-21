package bank.managment.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.dto.FonctionalityDto;
import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IPermissionService;
import bank.managment.backend.services.IRoleService;
import bank.managment.backend.trace.Trace;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
public class PermissionsController {
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPermissionService permissionService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRoles(){
		return ResponseEntity.ok(roleService.findAll());
	}
	
	@GetMapping("/fonctionalities")
	public ResponseEntity<List<FonctionalityDto>> getFonctionalities(){
		return ResponseEntity.ok(permissionService.getFonctionalities());
	}
	
	@PostMapping("/add-role")
	@Trace
	public ResponseEntity<?> addRole(@RequestBody Role role){
		if(role != null) {
			roleService.save(role);
			return ResponseEntity.ok(ResponseEntity.ok());
		}
		
		return ResponseEntity.ok(ResponseEntity.badRequest()
				.build()
				.getStatusCode());
		
	}
	
	@PostMapping("/save-role")
	@Trace
	public ResponseEntity<?> saveRole(@RequestBody Role role){
		if(role != null) {
			role.setPermissions(permissionService.saveAll(role.getPermissions()));
			roleService.save(role);
			return ResponseEntity.ok(ResponseEntity.ok());
		}
		
		return ResponseEntity.ok(ResponseEntity.badRequest()
				.build()
				.getStatusCode());
		
	}
}
