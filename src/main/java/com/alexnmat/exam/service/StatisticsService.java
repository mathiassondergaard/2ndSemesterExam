package com.alexnmat.exam.service;

import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.entities.Statistics;
import com.alexnmat.exam.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

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

    public void updateProjectHoursInStatistics(double totalHoursForProject, long projectId) {
        statisticsRepository.updateStatisticsProjectHours(projectId, totalHoursForProject);
    }

    public void updateSubProjectHoursInStatistics(double totalHoursForSubProjects, long projectId) {
        statisticsRepository.updateStatisticsSubProjectHours(projectId, totalHoursForSubProjects);
    }

    public Statistics findHoursForProject(long projectId) {
        return statisticsRepository.findByProjectId(projectId);
    }


}
