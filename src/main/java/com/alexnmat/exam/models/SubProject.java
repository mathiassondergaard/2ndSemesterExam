package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sub_project")
public class SubProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sub_project_id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_manager", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "sub_project_name")
    private String subProjectName;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date utilStartDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date utilEndDate;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "description")
    private String description;

    @Column(name = "allocated_hours")
    private int allocatedHours;

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    @OneToMany(mappedBy = "subProject", cascade = CascadeType.MERGE)
    private List<Task> tasks;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    public SubProject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
