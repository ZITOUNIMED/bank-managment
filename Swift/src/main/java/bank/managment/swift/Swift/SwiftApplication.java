package bank.managment.swift.Swift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SwiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftApplication.class, args);
	}

}
