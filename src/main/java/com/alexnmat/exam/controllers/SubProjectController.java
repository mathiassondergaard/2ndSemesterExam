package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.SubProject;
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
@RequestMapping("/dashboard/projects/")
public class SubProjectController {

    private SubProjectService subProjectService;
    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public SubProjectController(SubProjectService subProjectService, TaskService taskService, ProjectService projectService) {
        this.subProjectService = subProjectService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("{projectId}/subProjects")
    public String subProjectList(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.getAttribute("projects");
        model.addAttribute("subProjectsForProject", subProjectService.findSubProjectsForProject(projectId));
        model.addAttribute("type", 2);
        return "dashboard";

    }

    @GetMapping("{projectId}/subProjects/{subProjectId}")
    public String currentSubProject(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.getAttribute("projects");
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("tasks", taskService.getTaskDTOList(subProjectId));
        subProjectService.updateTotalTimeSpentForSubProject(subProjectId);
        model.addAttribute("type", 3);
        return "dashboard";
    }

    @GetMapping(value = "{projectId}/subProjects/createSubProject")
    public String showCreateSubProjectForm(@PathVariable("projectId") long projectId, SubProject subProject, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.addAttribute("type", 9);
        return "dashboard";
    }

    @PostMapping(value = "{projectId}/addSubProject")
    public String createNewSubProject(@PathVariable("projectId") long projectId, @Valid SubProject subProject, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", 9);
            model.addAttribute("subProject", subProject);
            model.addAttribute("type", 9);
            model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
            return "dashboard";
        }
        model.addAttribute("successMessage", "Sub-Project successfully created!");
        model.addAttribute("subProject", subProject);
        model.addAttribute("type", 9);
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        subProjectService.save(subProject, projectId);
        return "redirect:/dashboard/projects/" + projectId + "/subProjects/" + subProject.getId();
    }

    @GetMapping("{projectId}/subProjects/{subProjectId}/delete")
    public String deleteSubProject(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, Model model) {
        subProjectService.delete(subProjectId);
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        return "redirect:/dashboard/projects/" + projectId + "/subProjects/";
    }

    @GetMapping("{projectId}/subProjects/{subProjectId}/complete")
    public String completeSubproject(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, Model model) {
        subProjectService.complete(subProjectId);
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.addAttribute("currentSubProject", subProjectService.findSubProjectIdAndNameByProjectId(projectId, subProjectId));
        return "redirect:/dashboard/projects/" + projectId + "/subProjects/";
    }
}
