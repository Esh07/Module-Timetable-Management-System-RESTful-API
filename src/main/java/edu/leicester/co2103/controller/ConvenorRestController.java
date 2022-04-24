package edu.leicester.co2103.controller;

import java.util.List;
import edu.leicester.co2103.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.leicester.co2103.controller.info.ErrorInfo;
import edu.leicester.co2103.controller.info.SuccessInfo;
import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.exception.BadRequestException;
import edu.leicester.co2103.exception.ConvenorNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;

@RestController
public class ConvenorRestController {
	@Autowired
	ConvenorRepository repo;

	/*
	 * list all convenor
	 */
	// listing down all the module convenors
	@GetMapping("/convenors")
	public ResponseEntity<?> allConvenor() {

		// if empty then return http status no content
		if (((List<Convenor>) repo.findAll()).size() <= 0) {
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("No convenors found"), HttpStatus.NOT_FOUND);
		}
		// get all convenors from repo
		List<Convenor> convenors = (List<Convenor>) repo.findAll();
		// return list of content with http status code OK
		return new ResponseEntity<List<Convenor>>(convenors, HttpStatus.OK);
	}

	/*
	 * get specific convenor by id
	 */

	// get single convenor by id
	@GetMapping("/convenors/{id}")
	public ResponseEntity<?> getConvenor(@PathVariable Long id) {
		// fetch convenor by id from repo. if not found throw exception not found
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));

		// return convenor object with http status code OK
		return new ResponseEntity<Convenor>(convenor, HttpStatus.OK);
	}

	/*
	 * get all module taught by convenor
	 */
	@GetMapping("/convenors/{id}/modules")
	public ResponseEntity<?> convenorModule(@PathVariable Long id) {

		// fetch convenor by id from repo. if not found throw exception not found
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));

		if (convenor.getModules().size() <= 0) {
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("No module associated to convenor with id " + id),
					HttpStatus.NOT_FOUND);
		}
		// return list of modules taught by convenor with http status code OK
		return new ResponseEntity<>(convenor.getModules(), HttpStatus.OK);
	}

	/*
	 * add new convenor
	 */
	@PostMapping("/convenors")
	public ResponseEntity<?> newConvenor(@RequestBody Convenor convenor, UriComponentsBuilder ucBuilder) {

		// if newConvenor id not exist adds to repo and return http status created
		if (!(repo.existsById(convenor.getId()))) {
			// add new convenor to repo
			repo.save(convenor);

			HttpHeaders headers = new HttpHeaders();
			// set location header to url of newly created convenor
			headers.setLocation(ucBuilder.path("/convenors/{id}").buildAndExpand(convenor.getId()).toUri());

			// return new ResponseEntity<String>(headers, HttpStatus.CREATED);

			// return header with location of new resource and status code created with new
			// resources body
			return ResponseEntity.created(headers.getLocation()).headers(headers)
					.body(new SuccessInfo("New covenor successfully created"));

		} else if (repo.existsById(convenor.getId())) {
			// if id exist return conflict status
			return new ResponseEntity<ErrorInfo>(
					new ErrorInfo("Convenor with id " + convenor.getId() + "is already exists in the system."),
					HttpStatus.CONFLICT);
		} else
			// if something else then return bad request status
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("Invalid input data"),
					HttpStatus.BAD_REQUEST);
	}

	/*
	 * update a convenor information
	 */
	@PutMapping("/convenors/{id}")
	public ResponseEntity<?> updateConvenor(@PathVariable Long id, @RequestBody Convenor convenor) {

		Convenor currentConvenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));

		// if id not exist return bad request status
		if (repo.findById(id).isPresent()) {
			// update convenor name
			currentConvenor.setName(convenor.getName());
			// update convenor email
			currentConvenor.setPosition(convenor.getPosition());
			// clear convenor modules
			currentConvenor.getModules().clear();
			// add new convenor modules
			currentConvenor.getModules().addAll(convenor.getModules());
			// save to repo
			repo.save(currentConvenor);

			// return currentConvenor object and http status OK
			return new ResponseEntity<SuccessInfo>(new SuccessInfo("Convenor successfully updated"), HttpStatus.OK);
		} else
			// if id not exist return bad request status
			return new ResponseEntity<BadRequestException>(new BadRequestException(id),
					HttpStatus.BAD_REQUEST);

	}

	// delete a convenor
	@DeleteMapping("/convenors/{id}")
	public ResponseEntity<?> deleteConvenor(@PathVariable Long id) {

		// get convenor from repo by id if not then throw exception not found
		Convenor convenor = repo.findById(id)
				.orElseThrow(() -> new ConvenorNotFoundException(id));

		// check if convenor by id is present
		if (repo.findById(id).isPresent()) {
			// retrive module from repo by id
			List<Module> modules = convenor.getModules();
			// clear
			modules.clear();
			// delete from repo
			repo.delete(repo.findById(id).get());
			// retun deleted module with HttpStatus ok (200)
			return new ResponseEntity<SuccessInfo>(new SuccessInfo("Convenor with id " + id + " successfully deleted"),
					HttpStatus.OK);
		} else {
			// if not found return bad request status
			return new ResponseEntity<BadRequestException>(new BadRequestException(id), HttpStatus.BAD_REQUEST);
		}
	}

}
