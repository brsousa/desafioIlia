package br.com.desafio.livelo.ilia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.desafio.livelo.ilia.model.Person;
import br.com.desafio.livelo.ilia.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> getAll(){
		return personRepository.findAll();
	}
	
	public Optional<Person> findById(Long id){
		return personRepository.findById(id);
	}
	
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
	public Person update(Long id, Person person) {
		Person savedPerson = personRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(person, savedPerson, "id");
		return personRepository.save(savedPerson);
	}
	
	public void delete(Long id) {
		personRepository.deleteById(id);
	}
	
}
