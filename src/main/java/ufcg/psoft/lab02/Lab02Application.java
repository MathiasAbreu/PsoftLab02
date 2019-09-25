package ufcg.psoft.lab02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ufcg.psoft.lab02.controllers.DisciplinasController;
import ufcg.psoft.lab02.services.DisciplinasService;

@SpringBootApplication
public class Lab02Application {

	public static void main(String[] args) {

		SpringApplication.run(Lab02Application.class, args);
	}

}
