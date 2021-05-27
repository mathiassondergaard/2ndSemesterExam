package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.TaskDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.DTO.SubProjectDTO;
import com.alexnmat.exam.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.List;

@Service
public class SubProjectService extends Utilities {

    private SubProjectRepository subProjectRepository;
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    @Autowired
    public SubProjectService(SubProjectRepository subProjectRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.subProjectRepository = subProjectRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public SubProject findBySubProjectId(long subProjectId) {
        return subProjectRepository.findById(subProjectId)
                .orElseThrow(() -> new NoResultException("Unable to find subproject by id: " + subProjectId));
    }

    public SubProjectDTO findSubProjectIdAndNameByProjectId(long projectId, long subProjectId) {
        return subProjectRepository.findSubProjectIdAndNameByProjectId(projectId, subProjectId);
    }

    public List<SubProjectDTO> findSubProjectsForProject(long projectId) {
        return subProjectRepository.findAllByProjectId(projectId);
    }

    public SubProject save(SubProject subProject, long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoResultException("Unable to find project by id: " + projectId));
        subProject.setPerson(getCurrentLoggedInPerson());

        subProject.setProject(project);

        subProject.setAllocatedHours(calculateTotalWorkdayHours(subProject.getUtilStartDate(), subProject.getUtilEndDate()));
        subProject.setCompleted(false);
        if (dateChecker(subProject.getUtilStartDate(), subProject.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        if (!dateInBetweenChecker(project.getUtilStartDate(), subProject.getUtilStartDate(), project.getUtilEndDate(), subProject.getUtilEndDate())) {
            throw new DateTimeException("Sub Project Dates must be in between Project start/end dates!");
        }
        return subProjectRepository.save(subProject);
    }

    public void complete(long subProjectId) {
        subProjectRepository.setCompleted(subProjectId);
    }

    public void delete(long subProjectId) {
        SubProject subProject = findBySubProjectId(subProjectId);
        subProjectRepository.delete(subProject);
    }

    public void updateTotalTimeSpentForSubProject(long subProjectId) {
        double calculatedHours = calculateTotalHoursForSubProject(subProjectId);
        subProjectRepository.updateTotalTimeSpent(subProjectId, calculatedHours);
    }

    private double calculateTotalHoursForSubProject(long subProjectId) {
        double totalHours = 0;
        List<TaskDTO> taskStatistics = taskRepository.findTaskStatisticsBySubProjectId(subProjectId);
        for (int i = 0; i < taskStatistics.size(); i++) {
            totalHours += taskStatistics.get(i).getTotalTimeSpent();
        }
        return totalHours;
    }
}
