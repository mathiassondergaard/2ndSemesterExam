package com.alexnmat.exam.models.DTO;

import com.alexnmat.exam.models.entities.TeamMember;

import java.util.List;
import java.util.Objects;

public class ProjectDTO {

    private long id;

    private String projectName;

    public ProjectDTO(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public ProjectDTO(long id) {
        this.id = id;
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
}
