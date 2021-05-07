package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.Person;
import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.TeamMember;
import com.alexnmat.exam.service.PersonService;
import com.alexnmat.exam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/")
public class ProjectController {

    //https://stackoverflow.com/questions/27461283/how-to-access-fragment-in-fragment-from-controller
    //Fragments in controller
    //Update methods, add methods, should they return fragment or dashboard?
    //Maybe Posts need to have "RequestMapping "....", RequestType=GET, POST
    //TODO: Fix mappings:
    // return "project/" + projectId + "/addTeamMember"; not sure if this work ^ go on stacko
    // also exception throwing in findAll methods is nogo

    private ProjectService projectService;
    private PersonService personService;

    @Autowired
    public ProjectController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping("projects")
    public String projectList(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "dashboard";
    }

    @GetMapping("project/{projectId}")
    public String currentProject(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
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

    @GetMapping("project/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId") long projectId, Model model) {
        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        return "redirect:/dashboard/projects";
    }

    @GetMapping("project/{projectId}/edit")
    public String showUpdateProjectForm(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.findByProjectId(projectId);
        model.addAttribute("currentProject", project);
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));

        return "update-project";
    }

    @PostMapping("project/{projectId}/update")
    public String updateProject(@PathVariable("projectId") long projectId, @Valid Project project, BindingResult result, Model model) {
        if(result.hasErrors()) {
            project.setId(projectId);
            return "update-project";
        }

        projectService.save(project);
        model.addAttribute("successMessage", "Project successfully updated!");
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        return "redirect:/dashboard/project/" + projectId;
    }

    //TODO: Maybe add so you can add many team members by checking them from a list??
    @GetMapping("project/{projectId}/addTeamMember")
    public String showAddTeamMemberForm(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("persons", personService.findAll());
        return "project/" + projectId + "/addMember";
    }

    //TODO: Persons should be in a dropdown to be able to select
    //See this for help: https://stackoverflow.com/questions/58795016/how-do-i-pass-the-selected-dropdown-value-to-a-controller-in-thymeleaf
    @PostMapping("project/{projectId}/addMember")
    public String addTeamMember(@PathVariable("projectId") long projectId, @Valid TeamMember teamMember, @Valid Person person, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "project/" + projectId + "/addTeamMember";
        }
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("successMessage", "Team member successfully added!");
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        projectService.saveTeamMemberForProject(teamMember, person, projectId);
        return "redirect:/dashboard/project/" + projectId;
    }

    @GetMapping("project/{projectId}/deleteTeamMember/{teamMemberId}")
    public String deleteTeamMemberFromProject(@PathVariable("projectId") long projectId, @PathVariable("teamMemberId") long teamMemberId, Model model) {
        projectService.removeTeamMemberFromProject(projectId, teamMemberId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        return "redirect:/dashboard/project/" + projectId;
    }
}
