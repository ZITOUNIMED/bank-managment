package bank.managment.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.User;

@Repository
public interface UserDao extends CrudRepository<User, Long>{
	Optional<User> findByLogin(String login);
	List<User> findByRoleCode(String code);
}
