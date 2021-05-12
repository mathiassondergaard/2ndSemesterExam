package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.models.TeamMember;
import com.alexnmat.exam.models.TeamMemberHelper;
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
@RequestMapping(value = "/dashboard/projects/")
public class TeamMemberController {

    private ProjectService projectService;
    private PersonService personService;

    @Autowired
    public TeamMemberController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping("{projectId}/addTeamMember")
    public String showAddTeamMemberForm(@PathVariable("projectId") long projectId, Model model) {
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("teamMember", new TeamMemberHelper());
        return "add-team-member";
    }

    @PostMapping("{projectId}/addMember")
    public String addTeamMember(@PathVariable("projectId") long projectId, @Valid TeamMemberHelper teamMemberHelper, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-team-member";
        }

        model.addAttribute("persons", personService.findAll());
        model.addAttribute("teamMember", teamMemberHelper);
        model.addAttribute("successMessage", "Team members successfully added! Type another ID to add more");
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        try {
            projectService.saveTeamMemberForProject(new TeamMember(), teamMemberHelper.getPersonId(), projectId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred, please try again!");
            return "add-team-member";
        }
        return "redirect:/dashboard/projects/" + projectId + "/addTeamMember";
    }

    @GetMapping("{projectId}/deleteTeamMember/{teamMemberId}")
    public String deleteTeamMemberFromProject(@PathVariable("projectId") long projectId, @PathVariable("teamMemberId") long teamMemberId, Model model) {
        projectService.removeTeamMember(teamMemberId);
        model.addAttribute("projects", projectService.findProjectNamesAndIds());
        model.addAttribute("currentProject", projectService.findByProjectId(projectId));
        return "redirect:/dashboard/projects/" + projectId;
    }
}
