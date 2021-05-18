package com.alexnmat.exam.models.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "sub_task")
public class SubTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sub_task_id_seq", sequenceName = "sub_task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_task_id_seq")
    @Column(name = "sub_task_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @Column(name = "sub_task_name")
    @NotEmpty(message = "Please provide a name!")
    private String subTaskName;

    @Column(name = "start_date")
    @NotNull(message = "Please provide a date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate utilStartDate;

    @Column(name = "end_date")
    @NotNull(message = "Please provide a date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate utilEndDate;

    @Column(name = "completed")
    private Boolean completed;

    @NotEmpty(message = "Please provide a description!")
    @Length(max = 250, message = "Description cannot be longer than 250 characters!")
    @Column(name = "description")
    private String description;

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    public SubTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
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
}
