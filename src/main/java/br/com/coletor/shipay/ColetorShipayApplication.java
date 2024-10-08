package br.com.coletor.shipay;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class ColetorShipayApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ColetorShipayApplication.class);
    app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "devprd"));
    app.run(args);
  }

}