package com.alexnmat.exam.service;

import com.alexnmat.exam.models.SubProject;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.SubProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class SubProjectService extends AllocatedHoursCalculator {

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
        if (subProjectRepository.findAll().size() == 0) {
            throw new NoResultException("No projects available in database");
        }
        else {
            return subProjectRepository.findAll();
        }
    }

    public SubProject save(SubProject subProject, long personId, long projectId) {
        subProject.setPerson(personRepository.findById(personId)
                .orElseThrow(() -> new NoResultException("Unable to find person by id: " + personId)));

        subProject.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));

        subProject.setAllocatedHours(calculateTotalWorkdayHours(subProject.getUtilStartDate(), subProject.getUtilEndDate()));

        return subProjectRepository.save(subProject);
    }

    public void delete(long subProjectId) {
        SubProject subProject = findBySubProjectId(subProjectId);
        subProjectRepository.delete(subProject);
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
