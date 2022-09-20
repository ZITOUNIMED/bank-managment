package bank.managment.backend.mail;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class MailSender {
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
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
	
	public void sendHtmlMessage() throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Mohamed Zitouni");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Python", "Go", "C#", "Angular"));
        
        Context context = new Context();
        context.setVariables(properties);
        helper.setFrom(username);
        helper.setTo("zitouni.mohamed.email@gmail.com");
        helper.setSubject("Send Html Message");
        String html = templateEngine.process("welcome-email.html", context);
        helper.setText(html, true);

        emailSender.send(message);
	}
}
