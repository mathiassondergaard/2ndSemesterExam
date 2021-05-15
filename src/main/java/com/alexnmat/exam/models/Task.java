package com.alexnmat.exam.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @Column(name = "task_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sub_project_id", referencedColumnName = "sub_project_id")
    private SubProject subProject;

    @Column(name = "task_name")
    @NotEmpty(message = "Please provide a name!")
    private String taskName;

    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE)
    private List<SubTask> subTasks;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date!")
    @Column(name = "start_date")
    private LocalDate utilStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date!")
    @Column(name = "end_date")
    private LocalDate utilEndDate;

    @Column(name = "completed")
    private Boolean completed;

    @NotEmpty(message = "Please provide a description!")
    @Length(max = 250, message = "Description cannot be longer than 250 characters!")
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
