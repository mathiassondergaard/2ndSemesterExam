package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.models.entities.SubTask;
import com.alexnmat.exam.models.entities.Task;
import com.alexnmat.exam.service.ProjectService;
import com.alexnmat.exam.service.SubProjectService;
import com.alexnmat.exam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("dashboard/projects")
public class SubTaskController {

    private ProjectService projectService;
    private SubProjectService subProjectService;
    private TaskService taskService;

    @Autowired
    public SubTaskController(ProjectService projectService, SubProjectService subProjectService, TaskService taskService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
    }

    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId})")
    public String currentSubTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        model.addAttribute("currentSubproject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("tasks", taskService.getTaskDTOList(subProjectId));
        //subtaskId

        return "dashboard";
    }

    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/subTasks/createSubTask")
    public String showCreateSubTaskForm(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, SubProject subProject, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));
        return "add-sub-task";
    }

    @PostMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/subTasks/")
    public String createNewTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, @Valid SubProject subProject, @Valid Task task, @Valid SubTask subtask, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-task";
        }
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("successMessage", "Sub Task successfully created!");
        model.addAttribute("subProject", subProject);
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));
        taskService.saveSubTask(subtask, taskId);
        return "add-sub-task";
    }

    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/subTasks/{subTaskId}/complete")
    public String completeSubTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, @PathVariable("subTaskId") long subTaskId, Model model) {
        taskService.completeSubTask(subTaskId);
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));
        //model subTaskId
        return "redirect:/dashboard/projects" + projectId + "/subProjects/" + subProjectId + "/tasks/" + taskId + "/subTasks/";
    }

    @GetMapping(value = "{projectId}/subProjects/{subProjectId}/tasks/{taskId}/subTasks/{subTaskId}/delete")
    public String deleteSubTask(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, @PathVariable("taskId") long taskId, @PathVariable("subTaskId") long subTaskId, Model model) {
        taskService.deleteSubTask(taskId);
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("currentTask", taskService.findByTaskId(taskId));
        return "redirect:/dashboard/projects" + projectId + "/subProjects/" + subProjectId + "/tasks/" + taskId + "/subTasks/";

    }
    }

