package com.skyline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SkylineProjectApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static List<String> items = new ArrayList<String>();

	@RequestMapping("/")
	public String hello() {
		String test = "test123...";
		return test;
	}

	public static void main(String[] args) {
		SpringApplication.run(SkylineProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("hello world...");

		logger.info("Loading items details into memory...");
		
		
		String fileName = "src/main/java/com/skyline/data_sets/json_data.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				items.add(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found '" + fileName + "'");
		}

		logger.info("Loaded " + items.size() + " items successfully...");
	}
}
