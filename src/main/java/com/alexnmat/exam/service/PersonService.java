package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Department;
import com.alexnmat.exam.models.Person;
import com.alexnmat.exam.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

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

    public List<Person> findAll() {
        if (personRepository.findAll().size() == 0) {
            throw new NoResultException("No persons available in database");
        }
        else {
            return personRepository.findAll();
        }
    }

    public Person save(Person person, Department department) {
        person.setDepartments(();
        return personRepository.save(person);
    }

    public void delete(long personId) {
        Person person = findByPersonId(personId);
        personRepository.delete(person);
    }

}
