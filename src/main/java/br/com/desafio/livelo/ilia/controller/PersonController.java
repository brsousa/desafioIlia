package br.com.desafio.livelo.ilia.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.livelo.ilia.events.ResourceCreateEvent;
import br.com.desafio.livelo.ilia.model.Person;
import br.com.desafio.livelo.ilia.repository.PersonRepository;
import br.com.desafio.livelo.ilia.service.PersonService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 204, message = "No Content"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not found"),
		    @ApiResponse(code = 500, message = "Internal server error"),
	})
	@GetMapping(produces="application/json")
	public List<Person> listPeople(){
		return personRepository.findAll();
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 204, message = "No Content"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not found"),
		    @ApiResponse(code = 500, message = "Internal server error"),
	})
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 201, message = "Created"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not found"),
		    @ApiResponse(code = 500, message = "Internal server error"),
	})
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savedPerson = personRepository.save(person);
		eventPublisher.publishEvent(new ResourceCreateEvent(this, response, savedPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 204, message = "No Content"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not found"),
		    @ApiResponse(code = 500, message = "Internal server error"),
	})
	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person person){
		Person savedPerson = personService.update(id, person);
		return ResponseEntity.ok(savedPerson);
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 204, message = "No Content"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not found"),
		    @ApiResponse(code = 500, message = "Internal server error"),
	})
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletePerson (@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
}