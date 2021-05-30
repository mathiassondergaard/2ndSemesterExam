package com.alexnmat.exam.models.DTO;

/*
@Author: MSN
 */

public class ProjectMemberHelper {

    //Used for assisting the service method for adding a project member to a project.

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
