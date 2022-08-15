package bank.managment.backend.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bank.managment.backend.config.security.UserDetailsImpl;
import bank.managment.backend.dao.PermissionDao;
import bank.managment.backend.entities.Permission;
import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Permission save(Permission permission) {
		return permissionDao.save(permission);
	}

	@Override
	public Set<Permission> findAll() {
		return Streamable.of(permissionDao.findAll()).toSet();
	}

	@Override
	public Set<Permission> saveAll(Set<Permission> permissions) {
		Iterable<Permission> saved = permissionDao.saveAll(permissions);
		return Streamable.of(saved).toSet();
	}

	@Override
	public Role getUserRole() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userDetails.getRole();
	}
}
