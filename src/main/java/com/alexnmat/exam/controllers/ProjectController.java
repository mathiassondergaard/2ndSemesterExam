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

    //https://stackoverflow.com/questions/27461283/how-to-access-fragment-in-fragment-from-controller
    //Fragments in controller ( return "fragments/PAGE :: fragment"

    //We fetch all projects from the database due to our left menu on dashboard, that enables you to navigate between projects. However we only fetch name and ID.
    //Database optimization is therefore done in Service class.

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("projects")
    public String projectList(Model model) {
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        //model.addAttribute("projectsForTeamMember", projectService.findProjectsWhereLoggedInUserIsTeamMember());
        return "dashboard";
    }

    @GetMapping("projects/{projectId}")
    public String currentProject(@PathVariable("projectId") long projectId, Model model) {
        model.getAttribute("projects");
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("type", 1);
        model.addAttribute("teamMembersForProject", projectService.getAllTeamMembersForProject(projectId));
        return "dashboard";
    }

    @GetMapping(value = "projects/createProject")
    public String showCreateProjectForm(Project project) { return "add-project"; }

    @PostMapping(value = "projects/addProject")
    public String createNewProject(@Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-project";
        }
        model.addAttribute("successMessage", "Project successfully created!");
        model.addAttribute("project", project);
        projectService.save(project);
        return "redirect:/dashboard/projects";
    }

    @GetMapping("projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId") long projectId, Model model) {
        projectService.delete(projectId);
        return "redirect:/dashboard/projects";
    }

    //TODO: Havent done these two yet
    @GetMapping("projects/{projectId}/edit")
    public String showUpdateProjectForm(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.findByProjectId(projectId);
        model.addAttribute("currentProject", project);
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));

        return "update-project";
    }

    @PostMapping("projects/{projectId}/update")
    public String updateProject(@PathVariable("projectId") long projectId, @Valid Project project, BindingResult result, Model model) {
        if(result.hasErrors()) {
            project.setId(projectId);
            return "update-project";
        }

        projectService.save(project);
        model.addAttribute("successMessage", "Project successfully updated!");
        return "redirect:/dashboard/projects/" + projectId;
    }
}
