package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    Person findByName(String name);
}
