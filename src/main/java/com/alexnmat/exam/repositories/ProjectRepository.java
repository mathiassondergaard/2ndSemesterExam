package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    Project findByProjectName(String name);
}
