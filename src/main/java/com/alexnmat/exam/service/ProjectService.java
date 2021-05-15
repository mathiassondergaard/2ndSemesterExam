package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.ProjectDTO;
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

    //TODO: Different methods for getting projects based by role
    //TODO: Maybe fetch all projects one time, and thereafter ONLY when it updates? think about it

    public ProjectService() {
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    //TODO: We should maybe cache this
    public List<ProjectDTO> findProjectNamesAndIds() { return projectRepository.findProjectIdsAndNames(); }

    //If needed:
    public ProjectDTO findProjectNameAndId(long projectId) {
        return projectRepository.findProjectNameAndId(projectId);
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

    public List<TeamMember> getAllTeamMembersForProject(long projectId) {
        return teamMemberRepository.findAllByProjectId(projectId);
    }

    public void removeTeamMember(long teamMemberId) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(() -> new NoResultException("Unable to find team member by id: " + teamMemberId));
        teamMemberRepository.delete(teamMember);
    }


}
