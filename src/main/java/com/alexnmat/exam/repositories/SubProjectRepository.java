package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.DTO.SubProjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProject, Long> {

    @Query("SELECT new com.alexnmat.exam.models.DTO.SubProjectDTO(s.id, s.subProjectName, s.utilStartDate, s.utilEndDate, s.completed, s.person) FROM SubProject s WHERE s.project.id = :projectId")
    List<SubProjectDTO> findAllByProjectId(@Param("projectId") long projectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.SubProjectDTO(s.id, s.allocatedHours, s.totalTimeSpent) FROM SubProject s WHERE s.project.id = :projectId")
    List<SubProjectDTO> findSubProjectStatisticsByProjectId(@Param("projectId") long projectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.SubProjectDTO(s.id, s.subProjectName) FROM SubProject s WHERE s.project.id = :projectId AND s.id = :subProjectId")
    SubProjectDTO findSubProjectIdAndNameByProjectId(@Param("projectId") long projectId, @Param("subProjectId") long subProjectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SubProject SET completed = true WHERE id = :subProjectId")
    void setCompleted(@Param("subProjectId") long subProjectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SubProject SET totalTimeSpent = :totalHoursAfterCalculation WHERE id = :subProjectId")
    void updateTotalTimeSpent(@Param("subProjectId") long subProjectId, @Param("totalHoursAfterCalculation") double totalHoursAfterCalculation);

}
