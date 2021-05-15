package com.alexnmat.exam.service;

import com.alexnmat.exam.models.*;
import com.alexnmat.exam.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService extends Utilities {

    private SubProjectRepository subProjectRepository;

    private ProjectRepository projectRepository;

    private TaskRepository taskRepository;

    private PersonRepository personRepository;

    private HoursRepository hoursRepository;

    private SubTaskRepository subTaskRepository;

    @Autowired
    public TaskService(SubProjectRepository subProjectRepository, ProjectRepository projectRepository, TaskRepository taskRepository, PersonRepository personRepository, HoursRepository hoursRepository, SubTaskRepository subTaskRepository) {
        this.subProjectRepository = subProjectRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.hoursRepository = hoursRepository;
        this.subTaskRepository = subTaskRepository;
    }


    public SubProject findBySubProjectId(long subProjectId) {
        return subProjectRepository.findById(subProjectId)
                .orElseThrow(() -> new NoResultException("Unable to find sub project by id: " + subProjectId));
    }

    public List<TaskDTO> getTaskDTOList(long subProjectId) {
        return taskRepository.findAllByProjectId(subProjectId);
    }

    public List<Task> findAllTasksForSubProjectId(long subProjectId) {
        if (subProjectRepository.findAll().size() == 0) {
            throw new NoResultException("No sub projects available in database");
        } else {
            List<Task> fullList = taskRepository.findAll();
            List<Task> taskList = new ArrayList<>();
            for (int i = 0; i < fullList.size(); i++) {
                if (fullList.get(i).getSubProject().getId() == subProjectId) {
                    taskList.add(fullList.get(i));
                }
            }
            return taskList;
        }
    }


    public Task saveTask(Task task, long subProjectId) {
        SubProject subProject = subProjectRepository.findById(subProjectId)
                .orElseThrow(() -> new NoResultException("Unable to find sub project by id: " + subProjectId));
        if (dateChecker(task.getUtilStartDate(), task.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        task.setSubProject(subProject);
        return taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NoResultException("Unable to find task by id: " + taskId));
        taskRepository.delete(task);
    }


    public void completeTask(Task task, long taskId) {
        taskRepository.findById(taskId);
        task.setCompleted(true);
    }

    public int calculateTimeSpent(long taskId) {
        return taskRepository.findById(taskId).get().getTotalTimeSpent();
    }

    public SubProject findByTaskId(long taskId) {
        return subProjectRepository.findById(taskId)
                .orElseThrow(() -> new NoResultException("Unable to find task by id: " + taskId));
    }

    public List<SubTask> findAllSubTasksForTask(long taskId) {
        if (taskRepository.findAll().size() == 0) {
            throw new NoResultException("No sub projects available in database");
        } else {
            List<SubTask> fullList = subTaskRepository.findAll();
            List<SubTask> subTaskList = new ArrayList<>();
            for (int i = 0; i < fullList.size(); i++) {
                if (fullList.get(i).getTask().getId() == taskId) {
                    subTaskList.add(fullList.get(i));
                }
            }
            return subTaskList;
        }
    }

    public SubTask saveSubTask(SubTask subTask, long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoResultException("Unable to find task by id: " + taskId));
        if (dateChecker(subTask.getUtilStartDate(), subTask.getUtilEndDate())) {
            throw new DateTimeException("End date cannot be before start date!");
        }
        subTask.setTask(task);
        return subTaskRepository.save(subTask);

    }

    public void deleteSubTask(long subTaskId) {
        SubTask subTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new NoResultException("Unable to find sub task by id: " + subTaskId));
        subTaskRepository.delete(subTask);
    }

    public void completeSubTask(SubTask subTask, long subTaskId) {
        subTaskRepository.findById(subTaskId);
        subTask.setCompleted(true);
    }

}
