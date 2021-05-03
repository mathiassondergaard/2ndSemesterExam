package com.alexnmat.exam.repositories;
import com.alexnmat.exam.models.Effort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EffortRepository extends JpaRepository<Effort, Long> {
}
