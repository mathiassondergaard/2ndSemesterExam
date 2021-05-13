package com.alexnmat.exam.models;

import java.time.LocalDate;

public class SubProjectDTO {

    private long id;
    private String subProjectName;
    private LocalDate utilStartDate;
    private LocalDate utilEndDate;
    private Boolean completed;
    private Person person;

    public SubProjectDTO(long id, String subProjectName, LocalDate utilStartDate, LocalDate utilEndDate, Boolean completed, Person person) {
        this.id = id;
        this.subProjectName = subProjectName;
        this.utilStartDate = utilStartDate;
        this.utilEndDate = utilEndDate;
        this.completed = completed;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
