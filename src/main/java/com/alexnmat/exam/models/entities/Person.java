package com.alexnmat.exam.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
@Author: MSN & AFC
 */

@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "person_id")
    private long id;

    @OneToOne(mappedBy = "person")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "competence")
    private String competence;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private List<ProjectMember> projectMember;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Project> projects;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private List<SubProject> subProjects;

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

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public List<ProjectMember> getTeamMember() {
        return projectMember;
    }

    public void setTeamMember(List<ProjectMember> projectMember) {
        this.projectMember = projectMember;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
