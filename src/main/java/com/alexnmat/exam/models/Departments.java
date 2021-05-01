package com.alexnmat.exam.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "departments_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_name", referencedColumnName = "department_name")
    private Department departmentName;

    @OneToMany(mappedBy = "departments", cascade = CascadeType.MERGE)
    private List<Department> department;

    public Departments() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Department getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Department departmentName) {
        this.departmentName = departmentName;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }
}
