package org.binaracademy.bioskopbackend;

import org.binaracademy.bioskopbackend.controller.MovieController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BioskopbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopbackendApplication.class, args);

		MovieController movieController = new MovieController();
		movieController.mainMenu();
	}

}
