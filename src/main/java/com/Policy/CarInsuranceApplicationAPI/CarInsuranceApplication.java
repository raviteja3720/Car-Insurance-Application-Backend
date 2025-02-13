package com.Policy.CarInsuranceApplicationAPI;

import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInsuranceApplication.class, args);
		System.out.println("Application is started");
		TestLombok obj = new TestLombok();
		obj.setTestField("Hello");
		System.out.println(obj.getTestField());
	}

}

@Getter
@Setter
class TestLombok {
	private String testField;
}
