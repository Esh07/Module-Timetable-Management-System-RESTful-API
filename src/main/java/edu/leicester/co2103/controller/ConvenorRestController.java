package edu.leicester.co2103.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import edu.leicester.co2103.domain.Module;
import edu.leicester.co2103.exception.ModuleNoContentException;
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

import edu.leicester.co2103.controller.errorinfo.ErrorInfo;
import edu.leicester.co2103.controller.errorinfo.ErrorMessage;
import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.exception.ConvenorNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;

@RestController
public class ConvenorRestController {
	@Autowired
	ConvenorRepository repo;

	private final AtomicLong counter = new AtomicLong();

	/*
	 * list all convenor
	 */
	// listing down all the module convenors
	@GetMapping("/convenors")
	public ResponseEntity<List<Convenor>> allConvenor() {
		// get all convenors from repo
		List<Convenor> convenors = (List<Convenor>) repo.findAll();

		// if empty then return http status no content
		if (convenors.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// return list of content with http status code OK
		return new ResponseEntity<List<Convenor>>(convenors, HttpStatus.OK);
	}

	/*
	 * get specific convenor by id
	 */

	// get single convenor by id
	@GetMapping("/convenors/{id}")
	public ResponseEntity<?> getConvenor(@PathVariable Long id) {
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));
		return new ResponseEntity<>(convenor, HttpStatus.OK);
	}

	/*
	 * get al module taught by convenor
	 */
	@GetMapping("/convenors/{id}/modules")
	public ResponseEntity<?> convenorModule(@PathVariable Long id) {
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));
		if (convenor.getModules().isEmpty()) {
			throw new ModuleNoContentException(id);
		} else
			return new ResponseEntity<>(convenor.getModules(), HttpStatus.OK);
	}

	/*
	 * add new convenor
	 */
	@PostMapping("/convenors")
	public ResponseEntity<?> newConvenor(@RequestBody Convenor convenor) {

		// if newConvenor id not exist adds to repo and return http status created
		if (!(repo.existsById(convenor.getId()))) {
			repo.save(convenor);
			return new ResponseEntity<>(convenor.getId(), HttpStatus.CREATED);
		}
		return new ResponseEntity(convenor.getName() + "is already exists in the system.", HttpStatus.CONFLICT);
	}

	/*
	 * update a convenor information
	 */
	@PutMapping("/convenors/{id}")
	public ResponseEntity<?> updateConvenor(@PathVariable Long id, @RequestBody Convenor convenor) {

		if (repo.findById(id).isPresent()) {
			Convenor currentConvenor = repo.findById(id).get();
			currentConvenor.setName(convenor.getName());
			currentConvenor.setPosition(convenor.getPosition());
			currentConvenor.getModules().clear();
			currentConvenor.getModules().addAll(convenor.getModules());
			repo.save(currentConvenor);

			return new ResponseEntity<Convenor>(currentConvenor, HttpStatus.OK);
		} else
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("Convenor with id " + id + "not found."),
					HttpStatus.NOT_FOUND);

	}

	// delete a convenor
	@DeleteMapping("/convenors/{id}")
	public ResponseEntity<?> deleteConvenor(@PathVariable Long id) {
		Convenor convenor = repo.findById(id).get();
		if (repo.findById(id).isPresent()) {
			// retrive module from repo by id
			List<Module> modules = repo.findById(id).get().getModules();
			// clear
			modules.clear();
			// delete from repo
			repo.delete(repo.findById(id).get());
			// retun deleted module with HttpStatus ok (200)
			return new ResponseEntity<>(convenor, HttpStatus.OK);
		} else
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("There is no convenor with id " + id + "found. "),
					HttpStatus.NOT_FOUND);

	}

}
