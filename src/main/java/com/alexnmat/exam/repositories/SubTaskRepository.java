package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.DTO.SubTaskDTO;
import com.alexnmat.exam.models.DTO.TaskDTO;
import com.alexnmat.exam.models.entities.SubTask;
import com.alexnmat.exam.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    List<SubTask> findByTask(Task task);

    SubTask findByCompleted(Boolean completed);

    @Query("SELECT new com.alexnmat.exam.models.DTO.SubTaskDTO(st.id, st.subTaskName, st.utilStartDate, st.utilEndDate, st.completed) FROM SubTask st WHERE st.task.id = :taskId")
    List<SubTaskDTO> findDTOByTaskId(@Param("taskId") long taskId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SubTask SET completed = true WHERE id = :subTaskId")
    void setCompleted(@Param("subTaskId") long subTaskId);
}
