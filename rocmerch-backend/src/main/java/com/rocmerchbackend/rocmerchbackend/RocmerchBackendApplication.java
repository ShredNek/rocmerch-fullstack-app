package com.rocmerchbackend.rocmerchbackend;

import com.rocmerchbackend.rocmerchbackend.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RocmerchBackendApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(RocmerchBackendApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail() {
		senderService.sendEmail("danielleemusic98@gmail.com", "Subject is here: ", "Body is here");
	}

}
