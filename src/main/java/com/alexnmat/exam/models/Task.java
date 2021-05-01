package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "task_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sub_project_id", referencedColumnName = "sub_project_id")
    private SubProject subProject;

    @Column(name = "task_name")
    private String taskName;

    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE)
    private List<SubTask> subTasks;

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

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public String getSubProjectName() {
        return taskName;
    }

    public void setSubProjectName(String subProjectName) {
        this.taskName = subProjectName;
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

    public int getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(int totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
