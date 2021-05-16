package com.alexnmat.exam.models.DTO;

public class PersonDTO {

    private long id;
    private String name;
    private String lastName;
    private String competence;

    public PersonDTO(long id, String name, String lastName, String competence) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.competence = competence;
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
}
