package bank.managment.centralAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CentralAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralAdminApplication.class, args);
	}

}
