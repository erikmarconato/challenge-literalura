package com.alura.literalura;

import com.alura.literalura.service.BookService;
import com.alura.literalura.service.MainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	private final MainService mainService;

	public LiteraluraApplication(MainService mainService) {
		this.mainService = mainService;
	}

	@Override
	public void run(String... args) throws Exception {
		mainService.MenuOptions();
	}
}
