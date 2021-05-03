package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date utilStartDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date utilEndDate;

    @Column(name = "description")
    private String description;

    @Column(name = "allocated_hours")
    private int allocatedHours;

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_manager", referencedColumnName = "person_id")
    private Person person;

    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
    private List<Team> teams;

    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
    private List<Effort> efforts;

    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
    private List<Hours> hours;

    public Project() {
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

    public Date getUtilStartDate() {
        return utilStartDate;
    }

    public void setUtilStartDate(Date utilStartDate) {
        this.utilStartDate = utilStartDate;
    }

    public Date getUtilEndDate() {
        return utilEndDate;
    }

    public void setUtilEndDate(Date utilEndDate) {
        this.utilEndDate = utilEndDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAllocatedHours() {
        return allocatedHours;
    }

    public void setAllocatedHours(int allocatedHours) {
        this.allocatedHours = allocatedHours;
    }

    public int getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(int totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Effort> getEfforts() {
        return efforts;
    }

    public void setEfforts(List<Effort> efforts) {
        this.efforts = efforts;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }
}
