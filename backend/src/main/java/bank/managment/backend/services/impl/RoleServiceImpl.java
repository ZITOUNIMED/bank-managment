package bank.managment.backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import bank.managment.backend.dao.RoleDao;
import bank.managment.backend.entities.Role;
import bank.managment.backend.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleDao roleDao;
	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return Streamable.of(roleDao.findAll()).toList();
	}

	@Override
	public Optional<Role> findByCode(String code) {
		return roleDao.findByCode(code);
	}

}
