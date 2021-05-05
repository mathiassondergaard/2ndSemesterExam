package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.Team;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    private ProjectRepository projectRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, ProjectRepository projectRepository) {
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }

    public Team findByTeamId(long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NoResultException("Unable to find team by id: " + teamId));

    }

    public Team saveTeam(Team team, long projectId) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
        team.setProject(project);
        return teamRepository.save(team);
    }

    public void deleteTeam(long teamId) {
        findByTeamId(teamId);
        boolean teamExists = true;
        while(teamExists) {
            if (teamRepository.findAll().contains(teamId)) {
                Team team = teamRepository.findById(teamId)
                        .orElseThrow(() -> new NoResultException("Unable to find team by id: " + teamId))
                team.getId().remove(team);
            }
            else {
                teamExists = false;
            }
        }
        teamRepository.delete(team);
    }

}
