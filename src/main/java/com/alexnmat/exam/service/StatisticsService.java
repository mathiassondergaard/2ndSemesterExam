package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.DTO.SubProjectDTO;
import com.alexnmat.exam.models.DTO.SubTaskDTO;
import com.alexnmat.exam.models.DTO.TaskDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.entities.Statistics;
import com.alexnmat.exam.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService  {

    private StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public void createStatisticsTableForProject(Project project) {
        Statistics statistics = new Statistics();
        statistics.setProject(project);
        statisticsRepository.save(statistics);
    }

    public void updateProjectHoursInStatistics(int totalHoursForProject, long projectId) {
        statisticsRepository.updateStatisticsProjectHours(projectId, totalHoursForProject);
    }


    public void updateSubProjectHoursInStatistics(int totalHoursForSubProjects, long projectId) {
        statisticsRepository.updateStatisticsSubProjectHours(projectId, totalHoursForSubProjects);
    }

    public Statistics findHoursForProject(long projectId) {
        return statisticsRepository.findByProjectId(projectId);
    }


}
