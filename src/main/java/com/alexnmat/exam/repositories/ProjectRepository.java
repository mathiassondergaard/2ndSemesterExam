package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.ProjectDTO;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectName(String name);

    //Custom query. Solved our problem of retrieving ALL projects with all data, instead of just what we need.
    //https://thorben-janssen.com/spring-data-jpa-query-projections/ (13-05-2021)
    //Uses JPQL to make a DTO Projection via. the constructor in ProjectDTO
    @Query(value = "SELECT new com.alexnmat.exam.models.ProjectDTO(p.id, p.projectName) FROM Project p")
    List<ProjectDTO> findProjectIdsAndNames();

    //If needed
    @Query(value = "SELECT new com.alexnmat.exam.models.ProjectDTO(p.id, p.projectName) from Project p WHERE p.id = :projectId")
    ProjectDTO findProjectNameAndId(@Param("projectId") long projectId);

}
