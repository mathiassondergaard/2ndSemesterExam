package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.entities.SubTask;
import com.alexnmat.exam.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    List<SubTask> findByTask(Task task);

    SubTask findByCompleted(Boolean completed);

}
