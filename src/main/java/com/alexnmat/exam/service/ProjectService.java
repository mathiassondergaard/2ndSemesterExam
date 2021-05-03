package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProjectService {

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
        return projectRepository.findById(projectId);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project, long personId) {
        project.setPerson(personRepository.findById(personId));
        project.setAllocatedHours(calculateAllocatedHours(project.getUtilStartDate(), project.getUtilEndDate()));
        return projectRepository.save(project);
    }

    public void delete(long id) {
        Project project = projectRepository.findById(id);
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

    public long calculateAllocatedHours(LocalDate startDate, LocalDate endDate) {
        //Get startDay's value and endDay's value
        final int startDay = startDate.getDayOfWeek().getValue();
        final int endDay = endDate.getDayOfWeek().getValue();

        //Get days between start and end date
        final long days = ChronoUnit.DAYS.between(startDate, endDate);
        //Removes weekends from days
        long result = days - 2*(days/7);

        //Deal with the rest of the days
        if (days % 7 != 0) {
            if (startDay == 7) {
                result -= 1;
            } else if (endDay == 7) { //both days cant be sunday, would mean result == 0
                result -= 1;
            } else if (endDay < startDay) { //another weekend included.
                result -= 2;
            }
        }
        //One workday equals 8 hours
        int workdayHours = 8;

        return result * workdayHours;
    }


}
