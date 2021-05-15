package com.alexnmat.exam.models.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    //TODO: Add max length on project name etc.
    //TODO: Should project have a isCompleted boolean as in task?
    @Id
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    @NotEmpty(message = "Please provide a name!")
    private String projectName;

    @Column(name = "start_date")
    @NotNull(message = "Please provide a date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate utilStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date!")
    @Column(name = "end_date")
    private LocalDate utilEndDate;

    @NotEmpty(message = "Please provide a description!")
    @Length(max = 250, message = "Description cannot be longer than 250 characters!")
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

    public Project(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
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
