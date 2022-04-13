package edu.leicester.co2103.controller;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.leicester.co2103.controller.errorinfo.ErrorMessage;
import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.exception.ConvenorNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;

@RestController
public class ConvenorRestController {
	@Autowired
	ConvenorRepository repo;
	
	private final AtomicLong counter = new AtomicLong();
	
	
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
	
	
	@PostMapping("/convenors")
	public ResponseEntity<?> newConvenor(@RequestBody Convenor newConvenor) {
		
		//if newConvenor id not exist adds to repo and return http status created
		if(!repo.existsById(newConvenor.getId())) {
			
			repo.save(newConvenor);
			return new ResponseEntity<Convenor>(newConvenor, HttpStatus.CREATED);
		}
		
		return new ResponseEntity(newConvenor.getName() + "is already exists in the system.", HttpStatus.CONFLICT
				);
	}
	
	//get single convenor by id
	@GetMapping("/convenors/{id}")
	EntityModel<Convenor> oneConvenor(@PathVariable Long id) {
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));
		return EntityModel.of(convenor,
				linkTo(methodOn(ConvenorRestController.class).oneConvenor(id)).withSelfRel(),
						linkTo(methodOn(ConvenorRestController.class).allConvenor()).withRel("convenor"));
	}
	
	//update a convenor
	
	@PutMapping("/convenors/{id}")
	Convenor updateConvenor(@RequestBody Convenor newConvenor, @PathVariable Long id) {
		return repo.findById(id)
				.map(convenor -> {
					convenor.setName(newConvenor.getName());
					convenor.setPosition(newConvenor.getPosition());
					convenor.setModules(newConvenor.getModules());
					return repo.save(convenor);
				})
				.orElseGet(() -> {
					newConvenor.setId(id);
					return repo.save(newConvenor);
				});
	};
	
	
	//delete a convenor
	@DeleteMapping("/convenors/{id}")
	void deleteConvenor(@PathVariable Long id) {
		repo.deleteById(id);
		
	}
	
}
