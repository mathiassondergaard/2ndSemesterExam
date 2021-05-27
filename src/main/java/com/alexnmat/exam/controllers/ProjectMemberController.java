package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.ProjectMember;
import com.alexnmat.exam.models.DTO.ProjectMemberHelper;
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

@Controller
@RequestMapping(value = "/dashboard/projects/")
public class ProjectMemberController {

    private ProjectService projectService;
    private PersonService personService;

    @Autowired
    public ProjectMemberController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping("{projectId}/addProjectMember")
    public String showAddProjectMemberForm(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        model.addAttribute("persons", personService.findAllPersonsIdNameAndCompetence());
        model.addAttribute("projectMember", new ProjectMemberHelper());
        model.addAttribute("type", 8);
        return "dashboard";
    }

    @PostMapping("{projectId}/addMember")
    public String addProjectMember(@PathVariable("projectId") long projectId, @Valid ProjectMemberHelper projectMemberHelper, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projectMember", projectMemberHelper);
            model.addAttribute("type", 8);
            model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
            return "dashboard";
        }
        model.addAttribute("projectMember", projectMemberHelper);
        model.addAttribute("type", 8);
        model.addAttribute("successMessage", "Project member successfully added! Type another ID to add more");
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        projectService.saveProjectMemberForProject(new ProjectMember(), projectMemberHelper.getPersonId(), projectId);
        return "redirect:/dashboard/projects/" + projectId + "/addProjectMember";
    }

    @GetMapping("{projectId}/deleteProjectMember/{projectMemberId}")
    public String deleteTeamMemberFromProject(@PathVariable("projectId") long projectId, @PathVariable("projectMemberId") long projectMemberId, Model model) {
        projectService.removeProjectMember(projectMemberId);
        model.addAttribute("currentProject", projectService.findProjectNameAndId(projectId));
        return "redirect:/dashboard/projects/" + projectId;
    }
}
