package com.alexnmat.exam.models.DTO;

import com.alexnmat.exam.models.entities.TeamMember;

import java.util.List;
import java.util.Objects;

public class ProjectDTO {

    private long id;
    private String projectName;
    private long allocatedHours;
    private int totalTimeSpent;


    public ProjectDTO(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public ProjectDTO(long id) {
        this.id = id;
    }

    public ProjectDTO(long id, long allocatedHours, int totalTimeSpent) {
        this.id = id;
        this.allocatedHours = allocatedHours;
        this.totalTimeSpent = totalTimeSpent;
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

    public long getAllocatedHours() {
        return allocatedHours;
    }

    public void setAllocatedHours(long allocatedHours) {
        this.allocatedHours = allocatedHours;
    }

    public int getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(int totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }
}
