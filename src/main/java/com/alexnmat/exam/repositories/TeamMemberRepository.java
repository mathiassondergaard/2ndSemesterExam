package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByProject(Project project);

}
