package com.alexnmat.exam.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "team_id")
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teams_id", referencedColumnName = "teams_id")
    private Teams teams;



    @OneToMany(mappedBy = "team", cascade = CascadeType.MERGE)
    private List<Person> persons;

    public Team() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
