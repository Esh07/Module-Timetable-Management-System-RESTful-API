package edu.leicester.co2103.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.leicester.co2103.domain.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

}
