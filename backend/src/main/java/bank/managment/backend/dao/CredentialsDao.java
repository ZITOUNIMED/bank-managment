package bank.managment.backend.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.Credentials;

@Repository
public interface CredentialsDao extends CrudRepository<Credentials, Long>{
	Optional<Credentials> findByLogin(String login);
}
