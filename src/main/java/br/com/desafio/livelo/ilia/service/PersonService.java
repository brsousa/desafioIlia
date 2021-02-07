package br.com.desafio.livelo.ilia.service;

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
	
	public Person update(Long id, Person person) {
		Person savedPerson = personRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(person, savedPerson, "id");
		return personRepository.save(savedPerson);
	}
	
}
