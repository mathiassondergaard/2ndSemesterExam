package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.entities.SubTask;
import com.alexnmat.exam.models.entities.Task;
import com.alexnmat.exam.service.ProjectService;
import com.alexnmat.exam.service.SubProjectService;
import com.alexnmat.exam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/projects/")
public class TaskController {

    private ProjectService projectService;
    private SubProjectService subProjectService;
    private TaskService taskService;


    @Autowired
    public TaskController(ProjectService projectService, SubProjectService subProjectService, TaskService taskService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
    }

    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId})")
    public String currentTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("tasksForSubProject", taskService.findAllTasksForSubProjectId(subProjectId));
        model.addAttribute("subTasksForTask", taskService.findAllSubTasksForTask(taskId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));
        return "dashboard";
    }

    @GetMapping(value = "{subProjectId}/subProjects/createTask")
    public String showCreateTaskForm(@PathVariable("subProjectId") long subProjectId, SubProject subProject, Model model) {
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        return "add-task";
    }


    @PostMapping(value = "{projectId}/subProjects/{subProjectId}/addTask")
    public String createNewTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @Valid SubProject subProject, @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-task";
        }
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("successMessage", "Task successfully created!");
        model.addAttribute("subProject", subProject);
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        taskService.saveTask(task, subProjectId);
        return "add-task";
    }


    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/complete")
    public String completeTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, Model model) {
        //taskService.completeTask(taskId);
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));


        return "redirect:/dashboard/projects" + projectId + "/subProjects/" + subProjectId + "/tasks/";

    }


    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, Model model) {
        taskService.deleteTask(taskId);
        model.addAttribute("");

        return "redirect:/dashboard/projects" + projectId + "/subProjects/" + subProjectId + "/tasks/";
    }


}
