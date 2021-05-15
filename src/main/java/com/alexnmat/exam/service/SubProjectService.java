package com.alexnmat.exam.service;

import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.DTO.SubProjectDTO;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.List;

@Service
public class SubProjectService extends Utilities {

    private SubProjectRepository subProjectRepository;
    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    @Autowired
    public SubProjectService(SubProjectRepository subProjectRepository, ProjectRepository projectRepository, PersonRepository personRepository) {
        this.subProjectRepository = subProjectRepository;
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }

    public SubProject findBySubProjectId(long subProjectId) {
        return subProjectRepository.findById(subProjectId)
                .orElseThrow(() -> new NoResultException("Unable to find subproject by id: " + subProjectId));
    }

    public List<SubProject> findAll() {
        return subProjectRepository.findAll();
    }

    public List<SubProjectDTO> findSubProjectsForProject(long projectId) {
        return subProjectRepository.findAllByProjectId(projectId);
    }

    public SubProject save(SubProject subProject, long projectId) {
        subProject.setPerson(getCurrentLoggedInPerson());

        subProject.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));

        subProject.setAllocatedHours(calculateTotalWorkdayHours(subProject.getUtilStartDate(), subProject.getUtilEndDate()));
        subProject.setCompleted(false);
        if (dateChecker(subProject.getUtilStartDate(), subProject.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        return subProjectRepository.save(subProject);
    }

    public void complete(long subProjectId) {
        subProjectRepository.setCompleted(subProjectId);
    }

    public void delete(long subProjectId) {
        SubProject subProject = findBySubProjectId(subProjectId);
        subProjectRepository.delete(subProject);
    }
}
