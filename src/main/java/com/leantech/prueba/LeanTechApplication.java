package com.leantech.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LeanTechApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate template;
	
	public static void main(String[] args) {
		SpringApplication.run(LeanTechApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		template.execute("DROP TABLE person IF EXISTS");
		template.execute("DROP TABLE employee IF EXISTS");
		template.execute("DROP TABLE position IF EXISTS");
	
		template.execute("CREATE TABLE person(id IDENTITY PRIMARY KEY auto_increment, name VARCHAR(255) NULL, lastName VARCHAR(255) NULL, address VARCHAR(255) NULL, cellphone VARCHAR(255) NULL, cityName VARCHAR(255) NULL)");
		template.execute("CREATE TABLE employee(id IDENTITY PRIMARY KEY auto_increment, name VARCHAR(255) NULL, salary INT, person_id INT, position_id INT NULL)");
		template.execute("CREATE TABLE position(id IDENTITY PRIMARY KEY auto_increment, name VARCHAR(255) NULL)");
		
		template.execute("INSERT INTO employee(id, name, salary, person_id, position_id) VALUES (1, 'Micki', 112321, 1, 1)");
		template.execute("INSERT INTO employee(id, name, salary, person_id, position_id) VALUES (2, 'Benji', 223235, 1, 2)");
		template.execute("INSERT INTO employee(id, name, salary, person_id, position_id) VALUES (3, 'Negro', 336346, 2, 2)");
		template.execute("INSERT INTO employee(id, name, salary, person_id, position_id) VALUES (4, 'Perri', 445547, 2, 1)");
		template.execute("INSERT INTO employee(id, name, salary, person_id, position_id) VALUES (5, 'Perro', 556768, 2, 1)");
		
		template.execute("INSERT INTO person(id, name, lastName, address, cellphone, cityName) VALUES (1, 'Luis', 'Suarez', 'Cra 103B #82 - 48', '3102631399', 'Bogota')");
		template.execute("INSERT INTO person(id, name, lastName, address, cellphone, cityName) VALUES (2, 'Geral', 'Jimneez', 'cll 18A #72 B Sur - 43', '3124983131', 'Bogota')");
		
		template.execute("INSERT INTO position(id, name) VALUES (1, 'Cargo 1')");
		template.execute("INSERT INTO position(id, name) VALUES (2, 'Cargo 2')");
	}

}
