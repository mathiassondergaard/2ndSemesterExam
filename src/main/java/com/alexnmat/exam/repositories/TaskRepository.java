package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.SubProject;
import com.alexnmat.exam.models.SubProjectDTO;
import com.alexnmat.exam.models.Task;
import com.alexnmat.exam.models.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTaskName(String taskName);

    List<Task> findBySubProject(SubProject subProject);

    Task findByCompleted(Boolean completed);

    @Query("SELECT new com.alexnmat.exam.models.TaskDTO(t.id, t.taskName, t.utilStartDate, t.utilEndDate, t.completed) FROM Task t WHERE t.subProject.id = :subProjectId")
    List<TaskDTO> findAllByProjectId(@Param("subProjectId") long subProjectId);

}
