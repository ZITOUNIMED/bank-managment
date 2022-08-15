package bank.managment.backend.services;

import java.util.Set;

import bank.managment.backend.entities.Permission;
import bank.managment.backend.entities.Role;

public interface IPermissionService {
	Permission save(Permission permission);
	Set<Permission> findAll();
	Set<Permission> saveAll(Set<Permission> permissions);
	Role getUserRole();
}
