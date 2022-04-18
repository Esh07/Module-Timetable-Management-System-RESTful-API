package edu.leicester.co2103.controller;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import edu.leicester.co2103.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.exception.ConvenorNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;

@RestController
public class ConvenorRestController {
	@Autowired
	ConvenorRepository repo;
	
	private final AtomicLong counter = new AtomicLong();
	
	/*
	list all convenor
	 */
	//listing down all the module convenors
	@GetMapping("/convenors")
	public ResponseEntity<List<Convenor>> allConvenor(){
		//get all convenors from repo
		List<Convenor> convenors = (List<Convenor>) repo.findAll();
		
		//if empty then return http status no content 
		if (convenors.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//return list of content with http status code OK
		return new ResponseEntity<List<Convenor>>(convenors, HttpStatus.OK);
	}
	

	/*
	add new convenor
	 */
	@PostMapping("/convenors")
	public ResponseEntity<?> newConvenor(@RequestBody Convenor convenor) {
		
		//if newConvenor id not exist adds to repo and return http status created
		if(!(repo.existsById(convenor.getId()))) {
			repo.save(convenor);
			return new ResponseEntity<>(convenor.getId(), HttpStatus.CREATED);
		}
		return new ResponseEntity(convenor.getName() + "is already exists in the system.", HttpStatus.CONFLICT
				);
	}

	/*
	get specific convenor by id
	 */
	
	//get single convenor by id
	@GetMapping("/convenors/{id}")
	public ResponseEntity<?> getConvenor(@PathVariable Long id) {
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));
		return new ResponseEntity<>(convenor, HttpStatus.OK);
	}



	/*
		update a convenor information
	 */
	@PutMapping("/convenors/{id}")
	public ResponseEntity<?> updateConvenor(@PathVariable Long id, @RequestBody Convenor convenor) {

		repo.findById(id).map(convenorInfo -> {
					if (convenor.getName().isEmpty()){
						convenor.setName(repo.findById(id).get().getName());
					} else {
						convenor.setName(convenor.getName());
					}
					if (convenor.getPosition() == null){
						convenor.setPosition(repo.findById(id).get().getPosition());
					}else {
						convenor.setPosition(convenor.getPosition());
					}
					if (convenor.getModules() != null){
						List<Module> oldModules = repo.findById(id).get().getModules();
						List<Module> newModules = convenor.getModules();
						for (Module oldM:oldModules
							 ) {
							for (Module newM: newModules
								 ) {
								if(oldM.getCode() != newM.getCode()){
									convenor.setModules(convenor.getModules());
								}
							}
						}
					} else {
						convenor.setModules(repo.findById(id).get().getModules());
					}

					repo.save(convenor);
					return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
				})
				.orElseThrow(() -> new ConvenorNotFoundException(id));
		return new ResponseEntity<>(convenor, HttpStatus.NO_CONTENT);
	};
	
	
	//delete a convenor
	@DeleteMapping("/convenors/{id}")
	void deleteConvenor(@PathVariable Long id) {
		repo.deleteById(id);
		
	}
	
}
