package org.skypro.java.course2.examinerservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ExaminerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminerserviceApplication.class, args);
	}

}
