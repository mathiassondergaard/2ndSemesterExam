package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.Project;
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

    //TODO: vise alle subprojects når der klikkes på navbar
    //klikke ind på et subproject
    //få samme side som project info der viser alle tasks
    //klikke ind på task
    //give et view
    //for det specifikke task
    //klik ind på en subtask der står på tasks
    //så kan du se subtasken

    private SubProjectService subProjectService;
    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public SubProjectController(SubProjectService subProjectService, TaskService taskService, ProjectService projectService) {
        this.subProjectService = subProjectService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    //TODO: Maybe you need a custom query, to ONLY fetch the ID from database? so far name and ID
    @GetMapping("{projectId}/subProjects")
    public String subProjectList(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.getAttribute("projects");
        model.addAttribute("subProjectsForProject", subProjectService.findSubProjectsForProject(projectId));
        model.addAttribute("type", 2);
        return "dashboard";
    }

    @GetMapping("{projectId}/subProjects/{subProjectId}")
    public String currentProject(@PathVariable("projectId") long projectId, @PathVariable("subProjectId") long subProjectId, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.getAttribute("projects");
        model.addAttribute("currentSubProject", subProjectService.findBySubProjectId(subProjectId));
        model.addAttribute("tasks", taskService.getTaskDTOList(subProjectId));
        model.addAttribute("type", 3);
        return "dashboard";
    }

    @GetMapping(value = "{projectId}/subProjects/createSubProject")
    public String showCreateSubProjectForm(@PathVariable("projectId") long projectId, SubProject subProject, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        return "add-sub-project";
    }

    @PostMapping(value = "{projectId}/addSubProject")
    public String createNewSubProject(@PathVariable("projectId") long projectId, @Valid SubProject subProject, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-sub-project";
        }
        model.addAttribute("successMessage", "Sub-Project successfully created!");
        model.addAttribute("subProject", subProject);
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
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        return "redirect:/dashboard/projects/" + projectId;
    }
}
