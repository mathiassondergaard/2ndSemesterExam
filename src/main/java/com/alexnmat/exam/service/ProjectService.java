package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.TeamMemberDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.entities.TeamMember;
import com.alexnmat.exam.repositories.PersonRepository;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.List;

@Service
public class ProjectService extends Utilities {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private TeamMemberRepository teamMemberRepository;
    private SubProjectService subProjectService;
    private StatisticsService statisticsService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PersonRepository personRepository, TeamMemberRepository teamMemberRepository, SubProjectService subProjectService, StatisticsService statisticsService) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.subProjectService = subProjectService;
        this.statisticsService = statisticsService;
    }

    public ProjectService() {
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

    public void updateTotalTimeSpentForProject(long projectId) {
        double calculatedHours = subProjectService.calculateTotalHoursForAllSubProjects(projectId);
        statisticsService.updateProjectHoursInStatistics(calculatedHours, projectId);
        projectRepository.updateTotalTimeSpent(projectId, calculatedHours);
    }

    public TeamMember saveTeamMemberForProject(TeamMember teamMember, long personId, long projectId) {
        teamMember.setPerson(personRepository.findById(personId).orElseThrow(() -> new NoResultException("Unable to find person by id: " + personId)));
        teamMember.setProject(projectRepository.findById(projectId).orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId)));
        return teamMemberRepository.save(teamMember);
    }

    public List<TeamMemberDTO> getAllTeamMembersForProject(long projectId) {
        return teamMemberRepository.getTeamMembersIdAndPersonForProject(projectId);
    }

    public void removeTeamMember(long teamMemberId) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(() -> new NoResultException("Unable to find team member by id: " + teamMemberId));
        teamMemberRepository.delete(teamMember);
    }


}
