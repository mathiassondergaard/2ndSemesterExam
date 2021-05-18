package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.TeamMemberDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.entities.TeamMember;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.util.ArrayList;
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

    //TODO: Different methods for getting projects based by if you are a team member on that one
    //TODO: Maybe fetch all projects one time, and thereafter ONLY when it updates? think about it

    public ProjectService() {
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
    }

    public ProjectDTO findProjectNameAndId(long projectId) {
        return projectRepository.findProjectNameAndId(projectId);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    //TODO: We should maybe cache this
    public List<ProjectDTO> findProjectNamesAndIds() { return projectRepository.findProjectIdsAndNames(); }


    public Project save(Project project) {
        project.setPerson(getCurrentLoggedInPerson());
        project.setAllocatedHours(calculateTotalWorkdayHours(project.getUtilStartDate(), project.getUtilEndDate()));
        if (dateChecker(project.getUtilStartDate(), project.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        return projectRepository.save(project);
    }

    public void delete(long projectId) {
        Project project = findByProjectId(projectId);
        projectRepository.delete(project);
    }

    /*
    // Database optimization. Since we fetch all projects many times in the controller, we iterate through them in java to get a single project instead of fetching from DB.
    public Project findProjectById(long projectId, List<Project> projects) {
        Project foundProject = new Project();
        for (Project project : projects) {
            if (project.getId() == projectId) {
                foundProject = project;
            }
        }
        return foundProject;
    }
     */

    //TODO: fix this bullshit
    /*
    public List<ProjectDTO> findProjectsWhereLoggedInUserIsTeamMember() {
        List<ProjectDTO> projectsWithTeamMembers = projectRepository.findProjectIdsNamesAndTeamMembers();
        List<ProjectDTO> loggedInPersonsList = new ArrayList<>();
        long currentLoggedInPersonId = getCurrentLoggedInPerson().getId();

        for (int i = 0; i < projectsWithTeamMembers.size(); i++) {
            List<TeamMemberDTO> teamMemberDTOS = getAllTeamMembersForProject(projectsWithTeamMembers.get(i).getId());
            for (int x = 0; x < teamMemberDTOS.size(); x++) {
                if (teamMemberDTOS.get(i).getPerson().getId() == currentLoggedInPersonId) {


                }
            }
        }

        return projectRepository.findProjectIdsAndNamesForLoggedInPerson(personId);
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

    public TeamMember saveTeamMemberForProject(TeamMember teamMember, long personId, long projectId) {
        teamMember.setPerson(personRepository.findById(personId).orElseThrow(() -> new NoResultException("Unable to find person by id: " + personId)));
        teamMember.setProject(projectRepository.findById(projectId).orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));
        return teamMemberRepository.save(teamMember);
    }

    public TeamMember getSingleTeamMember(long projectId) {
        return teamMemberRepository.findSingleByProjectId(projectId);
    }

    public List<TeamMemberDTO> getAllTeamMembersForProject(long projectId) {
        return teamMemberRepository.getTeamMembersIdAndPersonForProject(projectId);
    }

    public void removeTeamMember(long teamMemberId) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(() -> new NoResultException("Unable to find team member by id: " + teamMemberId));
        teamMemberRepository.delete(teamMember);
    }


}
