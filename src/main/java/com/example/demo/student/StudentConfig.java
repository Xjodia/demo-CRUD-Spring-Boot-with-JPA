package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student maria = new Student(
                    "maria",
                    "maria@gmail.com",
                    LocalDate.of(2001, Month.JANUARY, 5)
            );

            Student james = new Student(
                    "james",
                    "james@gmail.com",
                    LocalDate.of(2002, Month.FEBRUARY, 7)
            );

            Student alex = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(2003, Month.FEBRUARY, 10)
            );
            repository.saveAll(
                    List.of(maria, james, alex)
            );
        };
    }
}
