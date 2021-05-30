package com.alexnmat.exam.controllers;

import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.models.entities.SubProject;
import com.alexnmat.exam.service.ProjectService;
import com.alexnmat.exam.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
@Author: MSN
 */

@Controller
@RequestMapping("/dashboard/projects/")
public class StatisticsController {

    private StatisticsService statisticsService;
    private ProjectService projectService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, ProjectService projectService) {
        this.statisticsService = statisticsService;
        this.projectService = projectService;
    }

    @GetMapping("{projectId}/statistics")
    public String showStatisticsForProject(@PathVariable("projectId") long projectId, Model model) {
        Project project = projectService.findByProjectId(projectId);
        List<SubProject> subProjects = project.getSubProjects();
        model.addAttribute("statistics", statisticsService.findHoursForProject(projectId));
        model.addAttribute("subProjects", subProjects);
        model.addAttribute("type", 6);
        model.addAttribute("currentProject", project);
        return "dashboard";
    }
}
