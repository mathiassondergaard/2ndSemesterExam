package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Hours;
import com.alexnmat.exam.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoursRepository extends JpaRepository<Hours, Long> {

    List<Hours> findByProject(Project project);
}
