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

@SpringBootApplication
public class Part1Application  implements ApplicationRunner {

	@Autowired
	private ConvenorRepository convenorRepo;
	
	@Autowired
	private ModuleRepository moduleRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	public static void main(String[] args) {
		SpringAppJSON.setup();
		SpringApplication.run(Part1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Session s1 = new Session();
		s1.setDatetime(Timestamp.valueOf("2022-02-25 12:00:00"));
		s1.setDuration(2);
		s1.setTopic("REST APIs");
		s1 = sessionRepo.save(s1);
		
		Module m1 = new Module("CO2103", "Software Architecture", 2, false);
		m1.getSessions().add(s1);
		m1 = moduleRepo.save(m1);
				
		Session s2 = new Session();
		s2.setDatetime(Timestamp.valueOf("2022-02-28 14:00:00"));
		s2.setDuration(2);
		s2.setTopic("Unit Testing");
		s2 = sessionRepo.save(s2);
		
		Module m2 = new Module("CO3095", "Software Quality", 3, true);
		m2.getSessions().add(s2);
		m2 = moduleRepo.save(m2);
		
		Convenor c1 = new Convenor("Jose Rojas", Position.PROFESSOR);
		c1.getModules().add(m1);
		c1 = convenorRepo.save(c1);
		
		Convenor c2 = new Convenor("Kehinde Aruleba", Position.LECTURER);
		c2.getModules().add(m1);
		c2.getModules().add(m2);
		c2 = convenorRepo.save(c2);
	}

	
}
