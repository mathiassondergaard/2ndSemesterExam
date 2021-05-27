package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.SubTaskDTO;
import com.alexnmat.exam.models.DTO.TaskDTO;
import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.entities.SubTask;
import com.alexnmat.exam.models.entities.Task;
import com.alexnmat.exam.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.List;

@Service
public class TaskService extends Utilities {

    private TaskRepository taskRepository;
    private SubTaskRepository subTaskRepository;
    private SubProjectService subProjectService;

    @Autowired
    public TaskService(SubProjectService subProjectService, TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
        this.subProjectService = subProjectService;
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    public Task findTaskById(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NoResultException("Unable to find Task by id: " + taskId));
    }

    public TaskDTO findTaskIdAndNameById(long taskId) {
        return taskRepository.findTaskIdAndNameById(taskId);
    }

    public List<TaskDTO> getTaskDTOList(long subProjectId) {
        return taskRepository.findAllByProjectId(subProjectId);
    }

    public Task saveTask(Task task, long subProjectId) {
        SubProject subProject = subProjectService.findBySubProjectId(subProjectId);
        if (dateChecker(task.getUtilStartDate(), task.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        if (!dateInBetweenChecker(subProject.getUtilStartDate(), task.getUtilStartDate(), subProject.getUtilEndDate(), task.getUtilEndDate())) {
            throw new DateTimeException("Task Dates must be in between Sub Project start/end dates!");
        }
        task.setAllocatedHours(calculateTotalWorkdayHours(task.getUtilStartDate(), task.getUtilEndDate()));
        task.setCompleted(false);
        task.setSubProject(subProject);
        return taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoResultException("Unable to find task by id: " + taskId));
        taskRepository.delete(task);
    }

    public void updateTotalTimeSpentForTask(long taskId) {
        double calculatedHours = calculateTotalHoursForTask(taskId);
        taskRepository.updateTotalTimeSpent(taskId, calculatedHours);
    }

    private double calculateTotalHoursForTask(long taskId) {
        double totalHours = 0;
        List<SubTaskDTO> subTaskStatistics = subTaskRepository.findStatisticsOnSubTasksByTaskId(taskId);
        for (int i = 0; i < subTaskStatistics.size(); i++) {
            totalHours += subTaskStatistics.get(i).getTotalTimeSpent();
        }
        return totalHours;
    }


    public void completeTask(long taskId) {
        taskRepository.setCompleted(taskId);
    }

    public SubTask findSubTaskById(long subTaskId) {
        return subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new NoResultException("Unable to find Sub-Task by id: " + subTaskId));
    }

    public List<SubTaskDTO> findAllSubTasksForTask(long taskId) {
        return subTaskRepository.findSubTasksIdNameStartDateEndDateAndCompletedByTaskId(taskId);
    }

    public SubTask saveSubTask(SubTask subTask, long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoResultException("Unable to find task by id: " + taskId));
        if (dateChecker(subTask.getUtilStartDate(), subTask.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        if (!dateInBetweenChecker(task.getUtilStartDate(), subTask.getUtilStartDate(), task.getUtilEndDate(), subTask.getUtilEndDate())) {
            throw new DateTimeException("Task Dates must be in between Task start/end dates!");
        }
        subTask.setAllocatedHours(calculateTotalWorkdayHours(subTask.getUtilStartDate(), subTask.getUtilEndDate()));
        subTask.setCompleted(false);
        subTask.setTask(task);
        return subTaskRepository.save(subTask);

    }

    public void deleteSubTask(long subTaskId) {
        SubTask subTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new NoResultException("Unable to find sub task by id: " + subTaskId));
        subTaskRepository.delete(subTask);
    }

    public void completeSubTask(long subTaskId) {
        subTaskRepository.setCompleted(subTaskId);
    }

    public void updateTotalTimeSpentForSubTask(long subTaskId, double newHours) {
        SubTaskDTO subTaskDTO = subTaskRepository.findStatisticsOnSubTaskBySubTaskId(subTaskId);
        double currentHours = subTaskDTO.getTotalTimeSpent();
        double calculatedHours = currentHours + newHours;
        subTaskRepository.updateTotalTimeSpent(subTaskId, calculatedHours);
    }

}
