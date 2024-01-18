package sg.edu.nus.iss.day27lecture;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import sg.edu.nus.iss.day27lecture.model.Person;
import sg.edu.nus.iss.day27lecture.repo.PersonRepo;

@SpringBootApplication
public class Day27LectureApplication implements CommandLineRunner {

	// @Autowired
	// MongoTemplate mt;

	@Autowired
	PersonRepo personRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day27LectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Person createdPerson = personRepo.insertPerson(new Person(10L, "Darryl", 15, "M", Arrays.asList("RC", "Swimmming", "Cycling")));
		System.out.println("Created Person:" + createdPerson.toString());

	}

}
