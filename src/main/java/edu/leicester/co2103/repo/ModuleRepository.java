package edu.leicester.co2103.repo;

import org.springframework.data.repository.CrudRepository;

import edu.leicester.co2103.domain.Module;

public interface ModuleRepository extends CrudRepository<Module, String> {

}
