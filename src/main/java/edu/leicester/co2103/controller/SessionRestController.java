package edu.leicester.co2103.controller;

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

@RestController
public class SessionRestController {

    @Autowired
    SessionRepository sessionRepo;

    @Autowired
    ModuleRepository moduleRepo;

    @Autowired
    ConvenorRepository convenorRepo;

    /*
     * 
     * Delete method to delete all sessions
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
     * Filter a session by module id and covenor id uding request paramerters
     */
    @GetMapping("/sessions")
    public ResponseEntity<?> filterSessions(@RequestParam(value = "module", required = false) String moduleId,
            @RequestParam(value = "convenor", required = false) Long convenorId) {

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

                List<Module> filteredSession = null;

                // check moduleId exists in convenor
                if (convenor.getModules().contains(module)) {
                    filteredSession = convenor.getModules();

                    for (Module m : filteredSession) {
                        if (m.getCode().equals(moduleId)) {
                            return new ResponseEntity<>(m.getSessions(), HttpStatus.OK);
                        }
                    }
                } else {
                    return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found for module with id "
                            + moduleId + " taught by convenor with id " + convenorId), HttpStatus.NOT_FOUND);
                }
            } else if (moduleId != null || convenorId != null) {

                // if only moduleId is passed
                if (moduleId != null) {

                    // get all the modules session from session
                    Module module = moduleRepo.findById(moduleId)
                            .orElseThrow(() -> new ModuleNotFoundException(moduleId));

                    List<Session> modulesSessions = module.getSessions();

                    return new ResponseEntity<>(modulesSessions, HttpStatus.OK);

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
        } else {
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("No sessions found"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<ErrorInfo>(new ErrorInfo("Invalid request"), HttpStatus.BAD_REQUEST);
    }

}
