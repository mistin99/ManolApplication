package com.example.Manol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.Manol")
@RestController
public class ManolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManolApplication.class, args);
	}

}
