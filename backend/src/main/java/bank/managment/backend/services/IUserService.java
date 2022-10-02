package bank.managment.backend.services;

import java.util.List;
import java.util.Optional;

import bank.managment.backend.entities.User;

public interface IUserService {
	User save(User ser);
	Optional<User> findById(Long id);
	Optional<User> findByLogin(String login);
	List<User> findAll();
	List<User> findByRoleCode(String code);
	void deleteById(Long id);
}
