package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.SubProject;
import com.alexnmat.exam.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(long id);

    Task findByTaskName(String taskName);

    List<Task> findBySubProject(SubProject subProject);

    Task findByCompleted(Boolean completed);
}
