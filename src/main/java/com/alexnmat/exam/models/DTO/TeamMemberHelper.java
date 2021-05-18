package com.alexnmat.exam.models.DTO;

import javax.validation.constraints.NotEmpty;

public class TeamMemberHelper {

    private long personId;

    public TeamMemberHelper(long personId) {
        this.personId = personId;
    }

    public TeamMemberHelper() {

    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
