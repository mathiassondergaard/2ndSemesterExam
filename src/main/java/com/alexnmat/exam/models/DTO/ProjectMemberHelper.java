package com.alexnmat.exam.models.DTO;

public class ProjectMemberHelper {

    private long personId;

    public ProjectMemberHelper(long personId) {
        this.personId = personId;
    }

    public ProjectMemberHelper() {

    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
