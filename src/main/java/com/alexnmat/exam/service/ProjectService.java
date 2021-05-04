package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService extends AllocatedHoursCalculator {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PersonRepository personRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }

    public ProjectService() {
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
    }

    public List<Project> findAll() {
        if (projectRepository.findAll().size() == 0) {
            throw new NoResultException("No projects available in database");
        }
        else {
            return projectRepository.findAll();
        }
    }

    public Project save(Project project, long personId) {
        project.setPerson(personRepository.findById(personId)
                .orElseThrow(() -> new NoResultException("Unable to find person by id: " + personId)));

        project.setAllocatedHours(calculateTotalWorkdayHours(project.getUtilStartDate(), project.getUtilEndDate()));
        return projectRepository.save(project);
    }

    public void delete(long projectId) {
        Project project = findByProjectId(projectId);
        projectRepository.delete(project);
    }
    /*
    public void deleteWishlist(long id) {
        Wishlist wishlist = wishlistRepository.findById(id);
        boolean wishExists = true;
        while(wishExists) {
            if (wishRepository.findAll().contains(id)) {
                Wish wish = wishRepository.findById(id);
                wishlist.getWishes().remove(wish);
            }
            else {
                wishExists = false;
            }
        }
        wishlistRepository.delete(wishlist);
    }
     */


}
