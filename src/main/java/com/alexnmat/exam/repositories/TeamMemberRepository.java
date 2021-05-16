package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.DTO.TeamMemberDTO;
import com.alexnmat.exam.models.entities.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findAllByProjectId(long projectId);

    TeamMember findSingleByProjectId(long projectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.TeamMemberDTO(t.id, t.person) FROM TeamMember t WHERE t.project.id = :projectId")
    List<TeamMemberDTO> getTeamMembersIdAndPersonForProject(long projectId);

}
