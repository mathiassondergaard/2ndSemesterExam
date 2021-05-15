package com.alexnmat.exam.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sub_project")
public class SubProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sub_project_id_seq", sequenceName = "sub_project_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_project_id_seq")
    @Column(name = "sub_project_id")
    private long id;

    @Column(name = "sub_project_name")
    @NotEmpty(message = "Please provide a name!")
    private String subProjectName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Please provide a date!")
    @Column(name = "start_date")
    private LocalDate utilStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Please provide a date!")
    @Column(name = "end_date")
    private LocalDate utilEndDate;

    @Column(name = "completed")
    private Boolean completed;

    @NotEmpty(message = "Please provide a description!")
    @Length(max = 250, message = "Description cannot be longer than 250 characters!")
    @Column(name = "description")
    private String description;

    @Column(name = "allocated_hours")
    private long allocatedHours;

    @Column(name = "total_time_spent")
    private int totalTimeSpent;

    @OneToMany(mappedBy = "subProject", cascade = CascadeType.MERGE)
    private List<Task> tasks;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "project_manager")
    private Person person;

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
