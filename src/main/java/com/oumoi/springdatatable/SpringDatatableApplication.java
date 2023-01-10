package com.oumoi.springdatatable;

import com.github.javafaker.Faker;
import com.oumoi.springdatatable.entity.Student;
import com.oumoi.springdatatable.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDatatableApplication {

	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDatatableApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(){

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {


				if (studentRepository.count() < 1000){
					for (int i = 0; i < 1000; i++) {
						Faker faker  = new Faker(new Locale("en-US"));

						String firstname  =  faker.name().firstName();
						String lastname   =  faker.name().lastName();

						Student student =  new Student();
						student.setEmail(firstname+lastname+"@gmail.com");
						student.setFirstName(firstname);
						student.setLastName(lastname);

						studentRepository.save(student);
					}
				}
			}
		};
	}

}
