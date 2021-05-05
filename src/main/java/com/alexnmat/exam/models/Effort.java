package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "effort")
public class Effort implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "effort_id_seq", sequenceName = "effort_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "effort_id_seq")
    @Column(name = "effort_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @Column(name = "analysis_hours")
    private int analysisHours;

    @Column(name = "design_hours")
    private int designHours;

    @Column(name = "coding_hours")
    private int codingHours;

    @Column(name = "testing_hours")
    private int testingHours;

    @Column(name = "project_management_hours")
    private int projectManagementHours;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate;

    public Effort() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getAnalysisHours() {
        return analysisHours;
    }

    public void setAnalysisHours(int analysisHours) {
        this.analysisHours = analysisHours;
    }

    public int getDesignHours() {
        return designHours;
    }

    public void setDesignHours(int designHours) {
        this.designHours = designHours;
    }

    public int getCodingHours() {
        return codingHours;
    }

    public void setCodingHours(int codingHours) {
        this.codingHours = codingHours;
    }

    public int getTestingHours() {
        return testingHours;
    }

    public void setTestingHours(int testingHours) {
        this.testingHours = testingHours;
    }

    public int getProjectManagementHours() {
        return projectManagementHours;
    }

    public void setProjectManagementHours(int projectManagementHours) {
        this.projectManagementHours = projectManagementHours;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }
}
