package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectName(String name);

    //Custom query. Solved our problem of retrieving ALL projects with all data, instead of just what we need. Is in HQL
    //SELECT object.attribute, object.attribute, FROM Entity object
    @Query("SELECT proj.id, proj.projectName FROM Project proj")
    List<Project> findProjectIdsAndNames();
}
