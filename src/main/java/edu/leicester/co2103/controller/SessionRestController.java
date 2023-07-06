package edu.leicester.co2103.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.leicester.co2103.domain.Session;
import edu.leicester.co2103.controller.info.ErrorInfo;
import edu.leicester.co2103.controller.info.SuccessInfo;
import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.domain.Module;
import edu.leicester.co2103.exception.BadRequestException;
import edu.leicester.co2103.exception.ConvenorNotFoundException;
import edu.leicester.co2103.exception.ModuleNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;
import edu.leicester.co2103.repo.ModuleRepository;
import edu.leicester.co2103.repo.SessionRepository;

/*
 * This is a controller class that handles all the requests related to sessions
 * 
 */
@RestController
public class SessionRestController {

    /*
     * Autowired SessionRepository object to access the database and perform CRUD
     */
    @Autowired
    SessionRepository sessionRepo;

    /*
     * Autowired ModuleRepository object to access the database and perform CRUD
     */
    @Autowired
    ModuleRepository moduleRepo;

    /*
     * Autowired ConvenorRepository object to access the database and perform CRUD
     */
    @Autowired
    ConvenorRepository convenorRepo;

    /*
     * Delete method to delete all sessions
     * 
     * @method: GET
     * 
     * @return ResponseEntity<?> - list of sessions with http status code OK
     */
    @DeleteMapping("/sessions")
    public ResponseEntity<?> deleteAllSessions() {
        // retrive all sessions
        List<Session> sessions = (List<Session>) sessionRepo.findAll();
        if (sessions.size() > 0) {
            // Delete all sessions
            sessionRepo.deleteAll();
            return new ResponseEntity<SuccessInfo>(new SuccessInfo("All sessions succesfully deleted"), HttpStatus.OK);

        } else {
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found"), HttpStatus.NOT_FOUND);
        }

    }

    /*
     * Filter a session by module id and convenor id using request parameters
     * 
     * @method: GET
     * 
     * @param: Long id - the id of the session to be deleted
     * 
     * @return ResponseEntity<?> - success message with http status code OK
     */
    @GetMapping("/sessions")
    public ResponseEntity<?> filterSessions(@RequestParam(value = "module", required = false) String moduleId,
            @RequestParam(value = "convenor", required = false) Long convenorId) {

        // check request parameters values is exact same

        // retrive all sessions
        List<Session> sessions = (List<Session>) sessionRepo.findAll();

        if (sessions.size() > 0) {
            // check moduleId and convenorId is passed
            if (moduleId != null && convenorId != null) {

                // get all the convenors session from session
                Convenor convenor = convenorRepo.findById(convenorId)
                        .orElseThrow(() -> new ConvenorNotFoundException(convenorId));

                // get all the modules session from session
                Module module = moduleRepo.findById(moduleId)
                        .orElseThrow(() -> new ModuleNotFoundException(moduleId));

                // check moduleId exists in convenor
                if (convenor.getModules().contains(module)) {
                    // get all the module from convenor
                    List<Module> filteredModule = convenor.getModules();
                    List<Session> moduleSessions = new ArrayList<>();
                    for (Module m : filteredModule) {
                        if (m.getCode().equals(moduleId)) {
                            moduleSessions.addAll(m.getSessions());
                        }
                    }
                    if (moduleSessions.size() > 0) {
                        return new ResponseEntity<List<Session>>(moduleSessions, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found for module with id "
                                + moduleId), HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<ErrorInfo>(
                            new ErrorInfo("Convenor with id " + convenorId + " does not teach module with id "
                                    + moduleId),
                            HttpStatus.NOT_FOUND);
                }
            }

            // check if either one parameter passed
            if (moduleId != null || convenorId != null) {

                // if only moduleId is passed
                if (moduleId != null) {

                    // get all the modules session from session
                    Module module = moduleRepo.findById(moduleId)
                            .orElseThrow(() -> new ModuleNotFoundException(moduleId));

                    List<Session> modulesSessions = module.getSessions();
                    if (modulesSessions.size() > 0) {
                        return new ResponseEntity<>(modulesSessions, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found for module with id "
                                + moduleId), HttpStatus.NOT_FOUND);
                    }

                } else {

                    // get all the convenors session from session
                    Convenor convenor = convenorRepo.findById(convenorId)
                            .orElseThrow(() -> new ConvenorNotFoundException(convenorId));

                    List<Session> allConvenorSessions = convenor.getModules().stream()
                            .flatMap(m -> m.getSessions().stream()).collect(java.util.stream.Collectors.toList());

                    // for (Module m : convenor.getModules()) {
                    // //apend all the session from convvenor.getModules()
                    // allConvenorSessions.add(m.getSessions());
                    // }
                    return new ResponseEntity<>(allConvenorSessions, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(sessionRepo.findAll(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found"), HttpStatus.NOT_FOUND);
    }

}
