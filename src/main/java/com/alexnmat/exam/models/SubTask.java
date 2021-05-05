package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "sub_task")
public class SubTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sub_task_id_seq", sequenceName = "sub_task_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_task_id_seq")
    @Column(name = "sub_task_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @Column(name = "sub_task_name")
    private String name;

    @Column(name = "start_date")
    private LocalDate utilStartDate;

    @Column(name = "end_date")
    private LocalDate utilEndDate;

    @Column(name = "completed")
    private Boolean completed;

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
