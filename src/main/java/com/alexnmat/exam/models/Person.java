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
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @Column(name = "person_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "competence")
    private String competence;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private List<Hours> hours;

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private List<TeamMember> teamMember;

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

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    public List<TeamMember> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(List<TeamMember> teamMember) {
        this.teamMember = teamMember;
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
