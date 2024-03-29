package edu.leicester.co2103.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import edu.leicester.co2103.controller.info.ErrorInfo;
import edu.leicester.co2103.controller.info.SuccessInfo;
import edu.leicester.co2103.domain.Convenor;
import edu.leicester.co2103.domain.Module;
import edu.leicester.co2103.domain.Session;
import edu.leicester.co2103.exception.BadRequestException;
import edu.leicester.co2103.exception.ModuleNotFoundException;
import edu.leicester.co2103.repo.ConvenorRepository;
import edu.leicester.co2103.repo.ModuleRepository;

/*
 * This is a controller class that handles all the requests related to modules
 * 
 */
@RestController
public class ModuleRestController {

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
     * get all modules from database
     * 
     * @method: GET
     * 
     * @return ResponseEntity<?> - list of modules with http status code OK
     * 
     * @throws ModuleNotFoundException if no module found
     */
    @GetMapping("/modules")
    public ResponseEntity<?> allModules() {
        List<Module> modules = (List<Module>) moduleRepo.findAll();
        // check if there are no modules in the repo then return http status no content
        if (modules == null || modules.size() == 0) {

            // return error info object with http status code not found
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Could not find any module"), HttpStatus.NOT_FOUND);
        }
        // return list of modules with http status code OK
        return new ResponseEntity<>(modules, HttpStatus.OK);

    }

    /*
     * get one module by code
     * 
     * @method: GET
     * 
     * @param: String code
     * 
     * @return ResponseEntity<?> - module object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * 
     */
    @GetMapping("/modules/{code}")
    public ResponseEntity<?> oneModule(@PathVariable String code) {

        // fetch module by code from repo. if not found throw exception not found
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // return module object with http status code OK
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    /*
     * update module by code
     * 
     * @method: PUT
     * 
     * @param: String code, Module module
     * 
     * @return ResponseEntity<?> - module object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code does not match
     * 
     * @throws BadRequestException if module code is null, empty or blank
     */
    @PostMapping("/modules")
    public ResponseEntity<?> addModule(@RequestBody Module module) {

        // check if module object not null. if null return error info object with http
        // status code bad request
        if (module.getCode() != null) {

            // current module
            Module newModule = module;

            // check if current module exists in repo.
            if (moduleRepo.existsById(module.getCode())) {

                // if exists return error info object with http status conflict
                return new ResponseEntity<ErrorInfo>(
                        new ErrorInfo("Module with id " + newModule.getCode() + " already exist"), HttpStatus.CONFLICT);
            }
            // if not exists add module to repo
            moduleRepo.save(newModule);

            // return module object with http status code created
            return new ResponseEntity<SuccessInfo>(new SuccessInfo("Module successfully created"),
                    HttpStatus.CREATED);

        } else
            // return error info object with http status code bad request
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Inavlid input data"), HttpStatus.BAD_REQUEST);
    }

    /*
     * update module by code
     * 
     * @method: PUT
     * 
     * @param: String code, Module module
     * 
     * @return ResponseEntity<?> - module object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code does not match
     * 
     * @throws BadRequestException if module code is null, empty or blank
     */
    @DeleteMapping("/modules/{code}")
    public ResponseEntity<?> deleteModule(@PathVariable String code) {
        // retrive module by id. if not found, throw exception return http status code
        // 404
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));
        // retrive all covenors
        Iterable<Convenor> convenor = convenorRepo.findAll();
        // check if module is assigned to any convenor or not. If yes then remove it
        // from convenor
        for (Convenor c : convenor) {
            if (c.getModules().contains(module)) {
                c.getModules().remove(module);
                convenorRepo.save(c);
            }
        }
        // after removing module from convenor then delete module
        moduleRepo.delete(module);
        // return http status No Content (201)
        return new ResponseEntity<SuccessInfo>(new SuccessInfo("Module successfully deleted"), HttpStatus.OK);
    }

    /*
     * update module by code
     * 
     * @method: PUT
     * 
     * @param: String code, Module module
     * 
     * @return ResponseEntity<?> - module object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code does not match
     * 
     * @throws BadRequestException if module code is null, empty or blank
     */

    @PatchMapping("/modules/{code}")
    public ResponseEntity<?> updateModule(@PathVariable String code, HttpEntity<Module> module) {

        // fetch module by id from repo. if not found throw exception not found
        Module module1 = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // check if request body has title value
        if (module.getBody().getTitle() != null) {
            // update title to module1
            module1.setTitle(module.getBody().getTitle());
        }

        // check if request body has level value
        if (String.valueOf(module.getBody().getLevel()) != null) {

            // update description to module1
            module1.setLevel(module.getBody().getLevel());
        }

        // check if request body has optional value
        if (String.valueOf(module.getBody().isOptional()) != null) {

            // update optional to module1
            module1.setOptional(module.getBody().isOptional());
        }

        // now save module1 to repo
        moduleRepo.save(module1);

        // return module object with http status code OK
        return new ResponseEntity<SuccessInfo>(new SuccessInfo("Module successfully updated"), HttpStatus.OK);
    }

    /*
     * get all sessions of a module
     * 
     * @method: GET
     * 
     * @param: String code
     * 
     * @return ResponseEntity<?> - list of sessions with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     */
    @GetMapping("/modules/{code}/sessions")
    public ResponseEntity<?> allSessions(@PathVariable String code) {

        // fetch module by id from repo. if not found throw exception not found
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // if module has no sessions then return http status no content
        if (module.getSessions().isEmpty()) {

            // return error info object with http status code not found
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("No session found for module " + code),
                    HttpStatus.NOT_FOUND);
        }

        // return list of sessions with http status code OK
        return new ResponseEntity<>(module.getSessions(), HttpStatus.OK);
    }

    /*
     * get session by id
     * 
     * @method: GET
     * 
     * @param: String code, int id
     * 
     * @return ResponseEntity<?> - session object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     * 
     * @throws BadRequestException if session id is null, empty or blank
     * 
     * @throws BadRequestException if session not found
     */
    @PostMapping("/modules/{code}/sessions")
    public ResponseEntity<?> addSession(@PathVariable String code, HttpEntity<Session> session) {

        if (session.getBody().getTopic() == null && session.getBody().getDatetime() == null
                && session.getBody().getDuration() <= 0) {
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Invalid input data"),
                    HttpStatus.BAD_REQUEST);
        }

        // incoming request body
        Session session1 = session.getBody();

        // check request body is valid

        // retrive module by id. if not found, throw exception return http status code
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // rettive all session availabe in module
        List<Session> existSessionList = module.getSessions();

        for (Session s : existSessionList) {
            // check if session already exist in module. throw exception if session with
            // same id
            if (s.getId() == session1.getId()) {
                // return http status code 409
                return new ResponseEntity<ErrorInfo>(new ErrorInfo("Session with id " + s.getId() + " already exist"),
                        HttpStatus.CONFLICT);
            }
        }

        // add session to module
        module.getSessions().add(session1);
        // save module
        moduleRepo.save(module);
        // return http status Created (201)
        return new ResponseEntity<SuccessInfo>(new SuccessInfo("Session successfully created"), HttpStatus.CREATED);
    }

    /*
     * get session by id
     * 
     * @method: GET
     * 
     * @param: String code, int id
     * 
     * @return ResponseEntity<?> - session object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     * 
     * @throws BadRequestException if session id is null, empty or blank
     * 
     * @throws BadRequestException if session not found
     */

    @GetMapping("/modules/{code}/sessions/{id}")
    public ResponseEntity<?> oneSession(@PathVariable String code, @PathVariable Long id) {
        // retrive module by id. if not found, throw exception return http status code
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));
        // retrive session by id. if not found, throw exception return http status code
        Optional<Session> session = module.getSessions().stream().filter(s -> s.getId() == id).findFirst();

        // if session not empty then return sessions and http status OK
        if (session.isPresent()) {
            // return session object with http status code OK
            return new ResponseEntity<>(session.get(), HttpStatus.OK);

        } else {

            // return error info object with http status code not found
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Could not find session with id " + id + " in module "),
                    HttpStatus.NOT_FOUND);
        }
    }

    /*
     * update session by id
     * 
     * @method: PUT
     * 
     * @param: String code, int id
     * 
     * @return ResponseEntity<?> - session object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     * 
     * @throws BadRequestException if session id is null, empty or blank
     * 
     * @throws BadRequestException if session not found
     */
    @DeleteMapping("/modules/{code}/sessions/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable String code, @PathVariable Long id) {

        // retrive module by id. if not found, throw exception return http status code
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // get session that has matches module code
        Optional<Session> session = module.getSessions().stream().filter(s -> s.getId() == id).findFirst();

        // check if session is present in module. if not throw error info object with
        // http status code not found
        if ((session.isPresent())) {

            // remove session from module
            module.getSessions().remove(session.get());
            // save module
            moduleRepo.save(module);
            // return SuccessInfo object with http status code OK
            return new ResponseEntity<SuccessInfo>(
                    new SuccessInfo("Session successfully deleted from module with code " + code),
                    HttpStatus.OK);
        } else {

            // return error info object with http status code not found
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Could not find session with id " + id + " in module."),
                    HttpStatus.NOT_FOUND);
        }
    }

    /*
     * update session by id
     * 
     * @method: PUT
     * 
     * @param: String code, int id
     * 
     * @return ResponseEntity<?> - session object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     * 
     * @throws BadRequestException if session id is null, empty or blank
     * 
     * @throws BadRequestException if session not found
     */
    @PatchMapping("/modules/{code}/sessions/{id}")
    public ResponseEntity<?> updateSpecificSessionInfo(@PathVariable String code, @PathVariable Long id,
            HttpEntity<Session> session) {

        // retrive module by id. if not found, throw exception return http status code
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // get session that matched session id
        Optional<Session> session1 = module.getSessions().stream().filter(s -> s.getId() == id).findFirst();

        // check session present in module. if not throw error info object with http
        // status code
        if (session1.isPresent()) {

            // check if request body has value for topic
            if (session.getBody().getTopic() != null) {
                // update topic
                session1.get().setTopic(session.getBody().getTopic());
            }

            // check if request body has value for date
            if (session.getBody().getDatetime() != null) {
                // update datetime
                session1.get().setDatetime(session.getBody().getDatetime());
            }
            // check if request body has value for duration
            if (session.getBody().getDuration() != 0) {

                // update duration
                session1.get().setDuration(session.getBody().getDuration());
            }

            // save session
            moduleRepo.save(module);

            // return session object and http status code 200 Successful
            return new ResponseEntity<SuccessInfo>(new SuccessInfo("Session successfully updated"), HttpStatus.OK);

        } else {
            // if session not found, throw exception return http status code NOT Found 404
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Could not find session with id " + id + " in module."),
                    HttpStatus.NOT_FOUND);
        }
    }

    /*
     * update all fields of session by using PUT method
     * 
     * @method: PUT
     * 
     * @param: String code, int id
     * 
     * @return ResponseEntity<?> - session object with http status code OK
     * 
     * @throws ModuleNotFoundException if module not found
     * 
     * @throws BadRequestException if module code is null, empty or blank
     * 
     * @throws BadRequestException if module has no sessions
     * 
     * @throws BadRequestException if session id is null, empty or blank
     * 
     * @throws BadRequestException if session not found
     */
    @PutMapping("/modules/{code}/sessions/{id}")
    public ResponseEntity<?> updateAllSessionInfo(@PathVariable String code, @PathVariable Long id,
            HttpEntity<Session> session) {

        // retrive module by id. if not found, throw exception not found and return http
        // status code Not Found
        Module module = moduleRepo.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));

        // get session that has matches session id
        Optional<Session> session1 = module.getSessions().stream().filter(s -> s.getId() == id).findFirst();

        // check session1 has value
        if (session1.isPresent()) {

            // update topic
            session1.get().setTopic(session.getBody().getTopic());

            // update datetime
            session1.get().setDatetime(session.getBody().getDatetime());

            // update duration
            session1.get().setDuration(session.getBody().getDuration());

            // save session
            moduleRepo.save(module);

            // return session object and http status code 200 Successful
            return new ResponseEntity<SuccessInfo>(new SuccessInfo("Session successfully updated"), HttpStatus.OK);
        } else {
            // if session not found, throw exception return http status code NOT Found 404
            return new ResponseEntity<ErrorInfo>(new ErrorInfo("Could not find session with id " + id + " in module."),
                    HttpStatus.NOT_FOUND);
        }
    }

}
