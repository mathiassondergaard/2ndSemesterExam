package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.Team;
import com.alexnmat.exam.repositories.ProjectRepository;
import com.alexnmat.exam.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    private ProjectRepository projectRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, ProjectRepository projectRepository) {
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }

    public Team findByTeamId(long id) {
        return teamRepository.findById(id);
    }

    public Team saveTeam(Team team, long id) {
        Project project = projectRepository.findById(id);
        team.setProject(project);
        return teamRepository.save(team);
    }

    public void deleteTeam(long id) {
        Team team = teamRepository.findById(id);
        boolean teamExists = true;
        while(teamExists) {
            if (teamRepository.findAll().contains(id)) {
                Team team = teamRepository.findById(id);
                team.getId().remove(team);
            }
            else {
                teamExists = false;
            }
        }
        teamRepository.delete(team);
    }

}
