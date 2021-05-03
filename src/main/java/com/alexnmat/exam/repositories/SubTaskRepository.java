package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.SubTask;
import com.alexnmat.exam.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    SubTask findById(long id);

    List<SubTask> findByTask(Task task);

}
