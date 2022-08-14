package bank.managment.backend.services;

import java.util.Optional;

import bank.managment.backend.entities.Credentials;

public interface ICredentialsService {
	Credentials save(Credentials credentials);
	Optional<Credentials> findById(Long id);
	Optional<Credentials> findByLogin(String code);
}
