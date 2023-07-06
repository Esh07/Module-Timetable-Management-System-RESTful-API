package edu.leicester.co2103.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.leicester.co2103.domain.Module;
import edu.leicester.co2103.domain.Session;

/*
 * This is a repository interface for Module entity 
 * 
 */
@Repository
public interface ModuleRepository extends CrudRepository<Module, String> {

}
