package com.Policy.CarInsuranceApplicationAPI;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarInsuranceApplication {

//    @Value("${username}")
//    String username;
//
//    @Value("${password}")
//    String password;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("username: " + username + "\n" + "password:" + password);
//    }


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
