package bank.managment.backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import bank.managment.backend.dao.UserDao;
import bank.managment.backend.entities.User;
import bank.managment.backend.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return userDao.findByLogin(login);
	}

	@Override
	public List<User> findAll() {
		return Streamable.of(userDao.findAll()).toList();
	}

	@Override
	public List<User> findByRoleCode(String code) {
		return userDao.findByRoleCode(code);
	}
	
	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

}
