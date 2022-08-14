package bank.managment.backend.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.managment.backend.dao.CredentialsDao;
import bank.managment.backend.entities.Credentials;
import bank.managment.backend.services.ICredentialsService;

@Service
public class CredentialsServiceImpl implements ICredentialsService {
	@Autowired
	private CredentialsDao credentialsDao;
	@Override
	public Credentials save(Credentials credentials) {
		return credentialsDao.save(credentials);
	}

	@Override
	public Optional<Credentials> findById(Long id) {
		return credentialsDao.findById(id);
	}

	@Override
	public Optional<Credentials> findByLogin(String login) {
		return credentialsDao.findByLogin(login);
	}

}
