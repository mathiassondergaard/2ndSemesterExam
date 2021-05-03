package com.alexnmat.exam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    @Test
    void calculateAllocatedHours() {
        ProjectService projectService = new ProjectService();
        LocalDate startDate = LocalDate.of(2021, 5, 3);
        LocalDate endDate = LocalDate.of(2021, 5, 7);
        assertEquals(96, projectService.calculateAllocatedHours(startDate, endDate));
    }
}