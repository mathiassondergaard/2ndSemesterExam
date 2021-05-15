package com.alexnmat.exam.repositories;
import com.alexnmat.exam.models.entities.Effort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EffortRepository extends JpaRepository<Effort, Long> {

    List<Effort> findByProjectId(long projectId);



}
