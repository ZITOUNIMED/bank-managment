package bank.managment.backend.services;

import java.util.List;
import java.util.Set;

import bank.managment.backend.dto.FonctionalityDto;
import bank.managment.backend.entities.Permission;

public interface IPermissionService {
	Permission save(Permission permission);
	Set<Permission> findAll();
	Set<Permission> saveAll(Set<Permission> permissions);
	List<FonctionalityDto> getFonctionalities();
}
