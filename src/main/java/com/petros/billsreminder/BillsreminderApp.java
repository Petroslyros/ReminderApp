package com.petros.billsreminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillsreminderApp {

	public static void main(String[] args) {
		SpringApplication.run(BillsreminderApp.class, args);
	}

}
