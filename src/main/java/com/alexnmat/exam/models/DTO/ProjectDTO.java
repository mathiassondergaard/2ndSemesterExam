package com.alexnmat.exam.models.DTO;

import com.alexnmat.exam.models.entities.TeamMember;

import java.util.List;
import java.util.Objects;

public class ProjectDTO {

    private long id;

    private String projectName;

    List<TeamMember> teamMembers;

    public ProjectDTO(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public ProjectDTO(long id, String projectName, List<TeamMember> teamMembers) {
        this.id = id;
        this.projectName = projectName;
        this.teamMembers = teamMembers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
