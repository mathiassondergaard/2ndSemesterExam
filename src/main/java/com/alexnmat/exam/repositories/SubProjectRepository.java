package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.SubProject;
import com.alexnmat.exam.models.SubProjectDTO;
import com.alexnmat.exam.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProject, Long> {

    SubProject findBySubProjectName(String subProjectName);

    SubProject findByCompleted(boolean completed);

    SubProject findByProject(Project projectId);

    @Query("SELECT new com.alexnmat.exam.models.SubProjectDTO(s.id, s.subProjectName, s.utilStartDate, s.utilEndDate, s.completed, s.person) FROM SubProject s WHERE s.project.id = :projectId")
    List<SubProjectDTO> findAllByProjectId(@Param("projectId") long projectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SubProject SET completed = true WHERE id = :subProjectId")
    void setCompleted(@Param("subProjectId") long subProjectId);
}
