package com.alexnmat.exam.models.DTO;

import javax.validation.constraints.NotEmpty;

public class TeamMemberHelper {

    @NotEmpty(message = "Please provide a id!")
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
