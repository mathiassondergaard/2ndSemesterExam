package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.DTO.ProjectMemberDTO;
import com.alexnmat.exam.models.entities.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    ProjectMember findSingleByProjectId(long projectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.ProjectMemberDTO(p.id, p.person) FROM ProjectMember p WHERE p.project.id = :projectId")
    List<ProjectMemberDTO> getTeamMembersIdAndPersonForProject(long projectId);

}
