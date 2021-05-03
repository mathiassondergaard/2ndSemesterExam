package com.alexnmat.exam.service;

import com.alexnmat.exam.models.SubProject;
import com.alexnmat.exam.models.Task;
import com.alexnmat.exam.models.Team;
import com.alexnmat.exam.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private SubProjectRepository subProjectRepository;

    private ProjectRepository projectRepository;

   private TaskRepository taskRepository;

   private PersonRepository personRepository;

   private HoursRepository hoursRepository;

    public TaskService(SubProjectRepository subProjectRepository, ProjectRepository projectRepository, TaskRepository taskRepository, PersonRepository personRepository, HoursRepository hoursRepository) {
        this.subProjectRepository = subProjectRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.hoursRepository = hoursRepository;
    }

    public List<Task> findAllTasksForSubProjectId(long subProjectId) {
        List<Task> fullList = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < fullList.size(); i++) {
            if  {

            }
        }
        return taskList;

    }

    public Task saveTask(Task task, long id) {
        SubProject subProject = subProjectRepository.findById(id);
        task.setSubProject(subProject);
        return taskRepository.save(task);
    }

    public void deleteTask(Task task, long id) {
        taskRepository.findById(id);
        boolean taskExists = true;
        while(taskExists) {
            if (taskRepository.findAll().contains(id)) {
                task.getId().remove(task);
            }
            else {
                taskExists = false;
            }
        }
        taskRepository.delete(task);
    }

    public void completeTask(Task task, long id) {
        taskRepository.findById(id);
        task.setCompleted(true);
    }

    public int calculateTimeSpent() {

    }



}
