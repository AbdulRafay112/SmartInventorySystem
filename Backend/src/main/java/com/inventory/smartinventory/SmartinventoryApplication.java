package com.inventory.smartinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartinventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartinventoryApplication.class, args);
		String name  = home();
		System.out.println(name);
	}
	public static String home() {
		return "Smart Inventory Backend is running!";
	}
}
