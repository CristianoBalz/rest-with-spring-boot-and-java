package br.com.cristianobalz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cristianobalz.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
