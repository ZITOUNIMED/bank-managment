package bank.managment.export.Export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportApplication.class, args);
	}

}
