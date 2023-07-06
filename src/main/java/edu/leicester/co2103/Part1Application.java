package edu.leicester.co2103;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.domain.Module;
import edu.leicester.co2103.domain.Position;
import edu.leicester.co2103.domain.Session;
import edu.leicester.co2103.repo.ConvenorRepository;
import edu.leicester.co2103.repo.ModuleRepository;
import edu.leicester.co2103.repo.SessionRepository;

/*
 * This is the main class of the application
 * 
 */
@SpringBootApplication
public class Part1Application implements ApplicationRunner {

	/*
	 * This is a repository for Convenor entity
	 */
	@Autowired
	private ConvenorRepository convenorRepo;

	/*
	 * This is a repository for Module entity
	 */
	@Autowired
	private ModuleRepository moduleRepo;

	/*
	 * This is a repository for Session entity
	 */
	@Autowired
	private SessionRepository sessionRepo;

	/*
	 * This is the main method of the application
	 * 
	 */
	public static void main(String[] args) {

		SpringAppJSON.setup();

		SpringApplication.run(Part1Application.class, args);
	}

	/*
	 * This is a method to run the application
	 * 
	 * @param args ApplicationArguments object to run the application with arguments
	 * 
	 * @return void
	 * 
	 * @throws Exception if any exception occurs while running the application
	 * 
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {

		// create a new session
		Session s1 = new Session();
		// set the datetime of the session
		s1.setDatetime(Timestamp.valueOf("2022-02-25 12:00:00"));
		// set the duration of the session
		s1.setDuration(2);
		// set the topic of the session
		s1.setTopic("REST APIs");
		// save the session to the database
		s1 = sessionRepo.save(s1);

		// create a new module - CO2103, Software Architecture, 2, false
		Module m1 = new Module("CO2103", "Software Architecture", 2, false);
		// add the session to the module
		m1.getSessions().add(s1);
		// save the module to the database
		m1 = moduleRepo.save(m1);

		// create a new session
		Session s2 = new Session();
		// set the datetime of the session
		s2.setDatetime(Timestamp.valueOf("2022-02-28 14:00:00"));
		// set the duration of the session
		s2.setDuration(2);
		// set the topic of the session
		s2.setTopic("Unit Testing");
		// save the session to the database
		s2 = sessionRepo.save(s2);

		// create a new module - CO3095, Software Quality, 3, true
		Module m2 = new Module("CO3095", "Software Quality", 3, true);
		// add the session to the module
		m2.getSessions().add(s2);
		// save the module to the database
		m2 = moduleRepo.save(m2);

		// create a new convenor - Jose Rojas, PROFESSOR
		Convenor c1 = new Convenor("Jose Rojas", Position.PROFESSOR);
		// add the module to the convenor - CO2103
		c1.getModules().add(m1);
		// save the convenor to the database
		c1 = convenorRepo.save(c1);

		// create a new convenor - Kehinde Aruleba, LECTURER
		Convenor c2 = new Convenor("Kehinde Aruleba", Position.LECTURER);
		// add the module to the convenor - CO2103, CO3095
		c2.getModules().add(m1);
		c2.getModules().add(m2);
		// save the convenor to the database
		c2 = convenorRepo.save(c2);
	}

}
