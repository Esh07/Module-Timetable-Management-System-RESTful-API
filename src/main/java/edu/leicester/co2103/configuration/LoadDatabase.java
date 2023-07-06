package edu.leicester.co2103.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.domain.Position;
import edu.leicester.co2103.repo.ConvenorRepository;

@Configuration
public class LoadDatabase {

	/*
	 * This is a logger object. It is used to log messages to the console.
	 */
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	/*
	 * This is a bean. It is a method that is called by Spring when the application
	 * starts.
	 * 
	 * The usecase of this bean is to populate the database with some data when the
	 * application starts.
	 */
	@Bean
	CommandLineRunner initDatabase(ConvenorRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Convenor("Bilbo Baggins", Position.LECTURER)));
			log.info("Preloading " + repository.save(new Convenor("Esh07", Position.GTA)));

		};
	}

}
