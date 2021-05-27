package com.alexnmat.exam.models.DTO;

import com.alexnmat.exam.models.entities.Person;
import com.alexnmat.exam.models.entities.Project;

public class ProjectMemberDTO {

    private long id;
    private Person person;
    private Project project;

    public ProjectMemberDTO(long id, Person person) {
        this.id = id;
        this.person = person;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
