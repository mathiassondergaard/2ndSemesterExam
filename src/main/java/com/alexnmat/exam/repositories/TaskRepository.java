package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.entities.Task;
import com.alexnmat.exam.models.DTO.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/*
@Author: AFC
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Multiple custom queries, used for DB optimization with DTO Projection

    @Query("SELECT new com.alexnmat.exam.models.DTO.TaskDTO(t.id, t.taskName, t.utilStartDate, t.utilEndDate, t.completed) FROM Task t WHERE t.subProject.id = :subProjectId")
    List<TaskDTO> findAllByProjectId(@Param("subProjectId") long subProjectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.TaskDTO(t.id, t.allocatedHours, t.totalTimeSpent) FROM Task t WHERE t.subProject.id = :subProjectId")
    List<TaskDTO> findTaskStatisticsBySubProjectId(@Param("subProjectId") long subProjectId);

    @Query("SELECT new com.alexnmat.exam.models.DTO.TaskDTO(t.id, t.taskName) FROM Task t WHERE t.id = :taskId")
    TaskDTO findTaskIdAndNameById(@Param("taskId") long taskId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Task SET completed = true WHERE id = :taskId")
    void setCompleted(@Param("taskId") long taskId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Task SET totalTimeSpent = :totalHoursAfterCalculation WHERE id = :taskId")
    void updateTotalTimeSpent(@Param("taskId") long taskId, @Param("totalHoursAfterCalculation") double totalHoursAfterCalculation);
}
