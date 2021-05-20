package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.DTO.SubProjectDTO;
import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.entities.Task;
import com.alexnmat.exam.service.ProjectService;
import com.alexnmat.exam.service.SubProjectService;
import com.alexnmat.exam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard/tasks/")
public class TaskController {

    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "{taskId}")
    public String currentTask(@PathVariable("taskId") long taskId, Model model) {
        model.getAttribute("projects");
        //TODO: comment why u did this shit
        Task task = taskService.findTaskById(taskId);
        ProjectDTO projectDTO = new ProjectDTO(task.getSubProject().getProject().getId());
        SubProjectDTO subProjectDTO = new SubProjectDTO(task.getSubProject().getId());
        model.addAttribute("currentSubProject", subProjectDTO);
        model.addAttribute("currentProject", projectDTO);
        model.addAttribute("currentTask", task);
        model.addAttribute("subTasksForTask", taskService.findAllSubTasksForTask(taskId));
        model.addAttribute("type", 4);
        return "dashboard";
    }

    @GetMapping(value = "createTask/{subProjectId}")
    public String showCreateTaskForm(@PathVariable("subProjectId") long subProjectId, Model model) {
        model.addAttribute("subProjectDTO", new SubProjectDTO(subProjectId));
        model.addAttribute("task", new Task());
        return "add-task";
    }

    //PASS REQUEST PARAM
    //FÅ REQUEST PARAM FRA SELECTED SUB PROJECT HTML
    //FIND SUB PROJECT VIA DEN
    @PostMapping(value = "addTask/{subProjectId}")
    public String createNewTask(@PathVariable("subProjectId") long subProjectId, @Valid Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-task";
        }
        model.addAttribute("successMessage", "Task successfully created!");
        model.addAttribute("task", task);
        taskService.saveTask(task, subProjectId);
        return "redirect:/dashboard/tasks/" + task.getId();
    }


    @GetMapping(value = "{taskId}/complete")
    public String completeTask(@PathVariable("taskId") long taskId, Model model) {
        //taskService.completeTask(taskId);
        //TODO: HARAM
        Task task = taskService.findTaskById(taskId);
        long projectId = task.getSubProject().getProject().getId();
        long subProjectId = task.getSubProject().getId();
        model.addAttribute("currentTask", task);
        taskService.completeTask(taskId);
        return "redirect:/dashboard/projects/" + projectId + "/subProjects/" + subProjectId;
    }


    @GetMapping(value = "{taskId}/delete")
    public String deleteTask(@PathVariable("taskId") long taskId, Model model) {
        Task task = taskService.findTaskById(taskId);
        long projectId = task.getSubProject().getProject().getId();
        long subProjectId = task.getSubProject().getId();
        taskService.deleteTask(taskId);
        return "redirect:/dashboard/projects/" + projectId + "/subProjects/" + subProjectId;
    }


}
