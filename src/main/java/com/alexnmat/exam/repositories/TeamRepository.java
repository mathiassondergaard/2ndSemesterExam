package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamMember, Long> {

    TeamMember findById(long id);

    TeamMember findByTeamName(String name);

    List<TeamMember> findByProject(Project project);

}
