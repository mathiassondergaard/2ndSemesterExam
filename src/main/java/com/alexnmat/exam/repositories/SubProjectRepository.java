package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProject, Long> {

    SubProject findBySubProjectName(String subProjectName);

    SubProject findByCompleted(boolean completed);

    SubProject findByProject(Project projectId);
}
