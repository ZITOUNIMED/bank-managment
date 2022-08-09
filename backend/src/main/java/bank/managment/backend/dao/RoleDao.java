package bank.managment.backend.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long>{
	Optional<Role> findByCode(String code);
}
