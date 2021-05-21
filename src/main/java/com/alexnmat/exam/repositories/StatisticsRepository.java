package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Statistics findByProjectId(long projectId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Statistics SET projectHours = :totalHoursAfterCalculation WHERE project.id = :projectId")
    void updateStatisticsProjectHours(@Param("projectId") long projectId, @Param("totalHoursAfterCalculation") double totalHoursAfterCalculation);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Statistics SET subProjectHours = :totalHoursAfterCalculation WHERE project.id = :projectId")
    void updateStatisticsSubProjectHours(@Param("projectId") long projectId, @Param("totalHoursAfterCalculation") double totalHoursAfterCalculation);


}
