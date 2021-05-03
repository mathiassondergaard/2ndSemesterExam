package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.Hours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoursRepository extends JpaRepository<Hours, Long> {
}
