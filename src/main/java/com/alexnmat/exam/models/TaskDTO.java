package com.alexnmat.exam.models;

import java.time.LocalDate;

public class TaskDTO {

    private long id;
    private String taskName;
    private LocalDate utilStartDate;
    private LocalDate utilEndDate;
    private Boolean completed;

    public TaskDTO(long id, String taskName, LocalDate utilStartDate, LocalDate utilEndDate, Boolean completed) {
        this.id = id;
        this.taskName = taskName;
        this.utilStartDate = utilStartDate;
        this.utilEndDate = utilEndDate;
        this.completed = completed;
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
}
