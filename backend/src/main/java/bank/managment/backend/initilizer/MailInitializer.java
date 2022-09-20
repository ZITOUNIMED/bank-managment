package bank.managment.backend.initilizer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import bank.managment.backend.mail.MailSender;

@Order(3)
@Profile("dev")
@Component
public class MailInitializer implements CommandLineRunner {

	@Autowired
	private MailSender mailSender;
	
	@Override
	public void run(String... args) throws Exception {
		mailSender.sendSimpleMessage("Hello from BankManagement Application.");
	}

}
