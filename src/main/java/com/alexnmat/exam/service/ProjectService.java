package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.ProjectMemberDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.entities.ProjectMember;
import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.List;

/*
@Author: MSN
 */

@Service
public class ProjectService extends Utilities {

    private ProjectRepository projectRepository;
    private ProjectMemberRepository projectMemberRepository;
    private PersonService personService;
    private StatisticsService statisticsService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PersonService personService, ProjectMemberRepository projectMemberRepository, StatisticsService statisticsService) {
        this.projectRepository = projectRepository;
        this.personService = personService;
        this.projectMemberRepository = projectMemberRepository;
        this.statisticsService = statisticsService;
    }

    //orElseThrow() used repeatedly, in order for us to throw custom exceptions with lambda functions.

    public ProjectService() {
    }

    //For Unit testing
    public ProjectService(ProjectRepository projectRepository) {
        super();
    }

    public Project findByProjectId(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
    }

    public ProjectDTO findProjectNameAndId(long projectId) {
        return projectRepository.findProjectNameAndId(projectId);
    }

    public List<ProjectDTO> findProjectNamesAndIds() {
        return projectRepository.findProjectIdsAndNames();
    }

    //Runs a single date-checker for validiation, uses Utilities method calculateTotalWorkdayHours for allocated hours before persisting.
    //Gets current authenticated in user as project Owner.
    public void save(Project project) {
        project.setPerson(getCurrentLoggedInPerson());
        project.setAllocatedHours(calculateTotalWorkdayHours(project.getUtilStartDate(), project.getUtilEndDate()));
        if (dateChecker(project.getUtilStartDate(), project.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        projectRepository.save(project);
        statisticsService.createStatisticsTableForProject(project);
    }

    public void delete(long projectId) {
        Project project = findByProjectId(projectId);
        projectRepository.delete(project);
    }

    //Iterates through a list of sub-projects in order to calculate total time spent on a project.
    public void updateTotalTimeSpentForProject(long projectId) {
        List<SubProject> subProjectList = findByProjectId(projectId).getSubProjects();
        double calculatedHours = 0;
        for (int i = 0; i < subProjectList.size(); i++) {
            calculatedHours += subProjectList.get(i).getTotalTimeSpent();
        }
        statisticsService.updateProjectHoursInStatistics(calculatedHours, projectId);
        projectRepository.updateTotalTimeSpent(projectId, calculatedHours);
    }

    public ProjectMember saveProjectMemberForProject(ProjectMember projectMember, long personId, long projectId) {
        projectMember.setPerson(personService.findByPersonId(personId));
        projectMember.setProject(projectRepository.findById(projectId).orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));
        return projectMemberRepository.save(projectMember);
    }

    public List<ProjectMemberDTO> getAllProjectMembersForProject(long projectId) {
        return projectMemberRepository.getTeamMembersIdAndPersonForProject(projectId);
    }

    public void removeProjectMember(long teamMemberId) {
        ProjectMember projectMember = projectMemberRepository.findById(teamMemberId).orElseThrow(() -> new NoResultException("Unable to find team member by id: " + teamMemberId));
        projectMemberRepository.delete(projectMember);
    }


}
