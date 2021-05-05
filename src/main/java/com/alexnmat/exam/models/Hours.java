package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hours")
public class Hours implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "hours_id_seq", sequenceName = "hours_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hours_id_seq")
    @Column(name = "hours_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "hours")
    private int hours;

    public Hours() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
