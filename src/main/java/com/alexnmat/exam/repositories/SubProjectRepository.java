package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProject, Long> {
}
