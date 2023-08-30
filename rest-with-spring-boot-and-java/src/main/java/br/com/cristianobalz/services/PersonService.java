package br.com.cristianobalz.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cristianobalz.data.vo.v1.PersonVO;
import br.com.cristianobalz.exceptions.ResourceNotFoundException;
import br.com.cristianobalz.mapper.DozerMapper;
import br.com.cristianobalz.model.Person;
import br.com.cristianobalz.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {	
		logger.info("Finding all peoples");		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	

	public PersonVO findById(Long id) {
		logger.info("Finding one person");
		
		var entity = repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("No record found for this id"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> 
			new ResourceNotFoundException("No record found for this id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		var entity = repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("No record found for this id"));
		
		repository.delete(entity);
	}


	
}
