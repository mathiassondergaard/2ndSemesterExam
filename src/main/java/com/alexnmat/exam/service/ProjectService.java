package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Person;
import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.TeamMember;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProjectService extends Utilities {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PersonRepository personRepository, TeamMemberRepository teamMemberRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    public ProjectService() {
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        project.setPerson(getCurrentLoggedInPerson());
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

    public void removeTeamMemberFromProject(long projectId, long teamMemberId) {
        Project project = findByProjectId(projectId);
        List<TeamMember> teamMembersForProject = project.getTeamMembers();
        for (int i = 0; i < teamMembersForProject.size(); i++) {
            if (teamMembersForProject.get(i).getId() == teamMemberId) {
                teamMembersForProject.remove(i);
            } else {
                throw new NoResultException("No Team Member for id" + teamMemberId + "exists in project");
            }
        }
        project.setTeamMembers(teamMembersForProject);
        projectRepository.save(project);
    }

    public TeamMember saveTeamMemberForProject(TeamMember teamMember, Person person, long projectId) {
        teamMember.setPerson(person);
        teamMember.setProject(projectRepository.findById(projectId).orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));
        return teamMemberRepository.save(teamMember);
    }

    public TeamMember getSingleTeamMember(long projectId) {
        return teamMemberRepository.findSingleByProjectId(projectId);
    }

    public List<TeamMember> getAllTeamMembersForProject(long projectId) {
        if (teamMemberRepository.findAllByProjectId(projectId).isEmpty()) {
            throw new NoResultException("No team members available in database");
        } else {
            return teamMemberRepository.findAllByProjectId(projectId);
        }
    }

    public void removeTeamMember(long teamMemberId) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(() -> new NoResultException("Unable to find team member by id: " + teamMemberId));
        teamMemberRepository.delete(teamMember);
    }


}
