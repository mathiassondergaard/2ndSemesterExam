package com.alexnmat.exam.service;

import com.alexnmat.exam.models.DTO.ProjectDTO;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectServiceTest extends Utilities {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateAllocatedHours() {

        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);
        assertEquals(32, calculateTotalWorkdayHours(startDate, endDate));
    }

    @Test
    void testDateValidiation() {

        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 4, 3);
        LocalDate validEndDate = LocalDate.of(2021, 6, 3);

        assertTrue(dateChecker(startDate, endDate));
        assertFalse(dateChecker(startDate, validEndDate));
    }

    @Test
    void findByProjectId() {

        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.of((new Project(1L, "thisName"))));

        Project project = projectRepository.findById(1L).orElseThrow(() -> new NoResultException("Unable to find project by id"));

        assertEquals("thisName", project.getProjectName());
    }

    @Test
    void findProjectNameAndId() {
        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);

        when(projectRepository.findProjectNameAndId(1L)).thenReturn(new ProjectDTO(1L, "projectName"));
        ProjectDTO projectDTO = projectRepository.findProjectNameAndId(1L);
        Project project = new Project(1L, "projectName", startDate, endDate, 200, 0);

        assertNotEquals(project, projectDTO);
        assertEquals(1L, projectDTO.getId());
        assertEquals("projectName", projectDTO.getProjectName());
    }

    @Test
    void findProjectNamesAndIds() {

        List<ProjectDTO> projectDTOList = new ArrayList<>();
        ProjectDTO projectOne = new ProjectDTO(1L, "nameOne");
        ProjectDTO projectTwo = new ProjectDTO(2L, "nameTwo");
        ProjectDTO projectThree = new ProjectDTO(3L, "nameThree");

        projectDTOList.add(projectOne);
        projectDTOList.add(projectTwo);
        projectDTOList.add(projectThree);

        when(projectRepository.findProjectIdsAndNames()).thenReturn(projectDTOList);

        //the test
        List<ProjectDTO> fetchedList = projectRepository.findProjectIdsAndNames();

        assertEquals(3, fetchedList.size());
        //Verify is used to ensure that the method is called.
        verify(projectRepository, Mockito.times(1)).findProjectIdsAndNames();
    }

    @Test
    void save() {
        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);
        Project project = new Project(1L, "projectName", startDate, endDate, 200, 0);

        projectRepository.save(project);

        verify(projectRepository, Mockito.times(1)).save(project);
    }

    @Test
    void delete() {
        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);
        Project project = new Project(1L, "projectName", startDate, endDate, 200, 0);

        projectRepository.delete(project);

        verify(projectRepository, Mockito.times(1)).delete(project);
    }

    @Test
    void updateTotalTimeSpentForProject() {
        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);
        Project project = new Project(1L, "projectName", startDate, endDate, 200, 0);

        double hoursForUpdate = 10;

        projectRepository.updateTotalTimeSpent(project.getId(), hoursForUpdate);

        verify(projectRepository, Mockito.times(1)).updateTotalTimeSpent(project.getId(), hoursForUpdate);
    }
}