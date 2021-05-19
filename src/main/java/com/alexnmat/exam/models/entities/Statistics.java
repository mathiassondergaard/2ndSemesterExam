package com.alexnmat.exam.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "statistics")
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "statistics_id_seq", sequenceName = "statistics_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_id_seq")
    @Column(name = "statistics_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @Column(name = "project_hours")
    private int projectHours;

    @Column(name = "sub_project_hours")
    private int subProjectHours;

    @Column(name = "task_hours")
    private int taskHours;

    @Column(name = "sub_task_hours")
    private int subTaskHours;

    public Statistics() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(int projectHours) {
        this.projectHours = projectHours;
    }

    public int getSubProjectHours() {
        return subProjectHours;
    }

    public void setSubProjectHours(int subProjectHours) {
        this.subProjectHours = subProjectHours;
    }

    public int getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(int taskHours) {
        this.taskHours = taskHours;
    }

    public int getSubTaskHours() {
        return subTaskHours;
    }

    public void setSubTaskHours(int subTaskHours) {
        this.subTaskHours = subTaskHours;
    }
}
