package com.allianceseeds.api;

import com.allianceseeds.api.services.UOWConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToolboxApplication {

	public static void main(String[] args) {
		// Capture the ApplicationContext
		ApplicationContext context = SpringApplication.run(ToolboxApplication.class, args);

		// Start the UOW consumer thread
//		UOWConsumer consumer = context.getBean(UOWConsumer.class);
//		Thread consumerThread = new Thread(consumer);
//		consumerThread.start();
//wkdpwkwdwdw
		System.out.println("running ....");
	}

	@Bean
	public UOWConsumer uowConsumer() {
		return new UOWConsumer();
	}
}