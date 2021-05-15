package com.alexnmat.exam.models.DTO;

import java.time.LocalDate;

public class SubTaskDTO {

    private long id;
    private String name;
    private LocalDate utilStartDate;
    private LocalDate utilEndDate;
    private Boolean completed;

    public SubTaskDTO(long id, String name, LocalDate utilStartDate, LocalDate utilEndDate, Boolean completed) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
