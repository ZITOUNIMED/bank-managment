package bank.managment.backend.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String smtpAuth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String tlsEnable;
	
	@Value("${spring.mail.properties.mail.debug}")
	private String mailDebug;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(host);
	    mailSender.setPort(port);
	    
	    mailSender.setUsername(username);
	    mailSender.setPassword(password);
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", smtpAuth);
	    props.put("mail.smtp.starttls.enable", tlsEnable);
	    props.put("mail.debug", mailDebug);
	    
	    return mailSender;
	}
}
