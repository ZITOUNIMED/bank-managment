package bank.managment.backend.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bank.managment.backend.constants.FonctionalitiesTypes;
import bank.managment.backend.dao.PermissionDao;
import bank.managment.backend.dto.FonctionalityDto;
import bank.managment.backend.entities.Permission;
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
	public List<FonctionalityDto> getFonctionalities() {
		return Arrays.stream(FonctionalitiesTypes.values())
				.map(fonctionality -> {
					return new FonctionalityDto(){{
						setName(fonctionality.name());
						setLabel(fonctionality.getLabel());
					}};
				})
				.collect(Collectors.toList());
	}
}

