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
    private double allocatedHours;

    @Column(name = "total_time_spent")
    private double totalTimeSpent;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_owner")
    private Person person;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Statistics> statistics;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<SubProject> subProjects;

    public Project() {
    }

    public Project(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    //For unit testing
    public Project(long projectId, String projectName, LocalDate utilStartDate, LocalDate utilEndDate, double allocatedHours, double totalTimeSpent) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<ProjectMember> getTeamMembers() {
        return projectMembers;
    }

    public void setTeamMembers(List<ProjectMember> teams) {
        this.projectMembers = teams;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
