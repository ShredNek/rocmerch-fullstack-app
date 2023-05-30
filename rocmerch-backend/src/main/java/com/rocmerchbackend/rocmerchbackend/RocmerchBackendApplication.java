package com.rocmerchbackend.rocmerchbackend;

import com.rocmerchbackend.rocmerchbackend.service.GMailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocmerchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocmerchBackendApplication.class, args);
	}
}
