package com.alexnmat.exam.models.DTO;

import java.time.LocalDate;

/*
@Author: MSN
 */

public class TaskDTO {

    //DTO's used for DTO Projection by JPA

    private long id;
    private String taskName;
    private LocalDate utilStartDate;
    private LocalDate utilEndDate;
    private Boolean completed;
    private double allocatedHours;
    private double totalTimeSpent;

    public TaskDTO(long id, String taskName, LocalDate utilStartDate, LocalDate utilEndDate, Boolean completed) {
        this.id = id;
        this.taskName = taskName;
        this.utilStartDate = utilStartDate;
        this.utilEndDate = utilEndDate;
        this.completed = completed;
    }

    public TaskDTO(long id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

    public TaskDTO(long id, double allocatedHours, double totalTimeSpent) {
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getUtilStartDate() {
        return utilStartDate;
    }

    public void setUtilStartDate(LocalDate utilStartDate) {
        this.utilStartDate = utilStartDate;
    }

    public LocalDate getUtilEndDate() {
        return utilEndDate;
    }

    public void setUtilEndDate(LocalDate utilEndDate) {
        this.utilEndDate = utilEndDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public double getAllocatedHours() {
        return allocatedHours;
    }

    public void setAllocatedHours(double allocatedHours) {
        this.allocatedHours = allocatedHours;
    }

    public double getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(double totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }
}
