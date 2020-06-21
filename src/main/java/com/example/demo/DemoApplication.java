package com.example.demo;

import com.example.demo.dto.response.Cep;
import com.example.demo.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ConsultService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<Cep> cep = service.consultCep("01032000");
		cep.ifPresent(System.out::println);
	}
}
