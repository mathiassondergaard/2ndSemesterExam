package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.PersonDTO;
import com.alexnmat.exam.models.entities.Person;
import com.alexnmat.exam.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findByPersonId(long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new NoResultException("Unable to find person by id: " + personId));
    }

    public List<PersonDTO> findAllPersonsIdNameAndCompetence() {
        return personRepository.findAllPersonsIdNameAndCompetence();
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void delete(long personId) {
        Person person = findByPersonId(personId);
        personRepository.delete(person);
    }

}
