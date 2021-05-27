package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/dashboard/")
public class ProjectController {

    //We fetch all projects from the database due to our left menu on dashboard, that enables you to navigate between projects. However we only fetch name and ID.
    //Database optimization is therefore done in respective Repository class.

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("projects")
    public String projectList(Model model) {
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        return "dashboard";
    }

    @GetMapping("projects/{projectId}")
    public String currentProject(@PathVariable("projectId") long projectId, Model model) {
        model.getAttribute("projects");
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("type", 1);
        model.addAttribute("projectMembersForProject", projectService.getAllProjectMembersForProject(projectId));
        projectService.updateTotalTimeSpentForProject(projectId);
        return "dashboard";
    }

    @GetMapping(value = "projects/createProject")
    public String showCreateProjectForm(Project project, Model model) {
        model.addAttribute("type", 7);
        return "dashboard";
    }

    @PostMapping(value = "projects/addProject")
    public String createNewProject(@Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", 7);
            model.addAttribute("project", project);
            return "dashboard";
        }
        model.addAttribute("successMessage", "Project successfully created!");
        model.addAttribute("type", 8);
        model.addAttribute("project", project);
        projectService.save(project);
        return "redirect:/dashboard/projects";
    }

    @GetMapping("projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId") long projectId, Model model) {
        projectService.delete(projectId);
        return "redirect:/dashboard/projects";
    }
}
