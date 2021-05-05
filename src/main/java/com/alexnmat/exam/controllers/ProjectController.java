package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/projects/")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "createProject")
    public String showCreateProjectForm(Project project) { return "add-project"; }

    @PostMapping(value = "addProject")
    public String createNewProject(@Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-project";
        }
        model.addAttribute("successMessage", "Project successfully created!");
        model.addAttribute("project", project);
        projectService.save(project);
        return "redirect:/projects/list";
    }



}
