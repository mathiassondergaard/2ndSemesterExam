package com.alexnmat.exam.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    //TODO: Add max length on project name etc.
    @Id
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate utilStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate utilEndDate;

    @Column(name = "description")
    private String description;

    @Column(name = "allocated_hours")
    private long allocatedHours;

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_owner")
    private Person person;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Effort> efforts;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Hours> hours;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<SubProject> subProjects;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teams) {
        this.teamMembers = teams;
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

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
