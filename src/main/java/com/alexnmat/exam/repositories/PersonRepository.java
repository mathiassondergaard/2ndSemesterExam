package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.DTO.PersonDTO;
import com.alexnmat.exam.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT new com.alexnmat.exam.models.DTO.PersonDTO(p.id, p.name, p.lastName, p.competence) FROM Person p")
    List<PersonDTO> findAllPersonsIdNameAndCompetence();
}
