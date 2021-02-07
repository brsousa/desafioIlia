package br.com.desafio.livelo.ilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.livelo.ilia.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
