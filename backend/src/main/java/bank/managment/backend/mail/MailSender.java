package bank.managment.backend.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
	@Autowired
    private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	public void sendSimpleMessage(String text) {
	    SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(username);
        message.setTo("zitouni.mohamed.email@gmail.com"); 
        message.setSubject("Example Subject"); 
        message.setText(text);
        emailSender.send(message);
	}
}
