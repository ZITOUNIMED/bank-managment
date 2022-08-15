package bank.managment.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IPermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {
	@Autowired
	private IPermissionService permissionService;
	
	@GetMapping("/user-role")
	public ResponseEntity<Role> getUserRole(){
		return ResponseEntity.ok(permissionService.getUserRole());
	}
}
