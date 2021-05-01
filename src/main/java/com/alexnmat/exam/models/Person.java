package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id")
    private long id;

    @OneToOne(mappedBy = "person")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_departments", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "departments_id"))
    private Set<Departments> departments;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Hours> hours;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @OneToOne(mappedBy = "person")
    private Project project;

    @OneToOne(mappedBy = "person")
    private SubProject subProject;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Departments> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Departments> departments) {
        this.departments = departments;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }


}
