package com.webflex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class  WebfluxProjectApplication{


  public static void main(String[] args) {
    SpringApplication.run( WebfluxProjectApplication.class, args);
  }

}
