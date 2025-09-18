package com.devdojo.domain;

import com.devdojo.domain.enums.Benefits;

public class Manager extends Employee {
    private String departament;
    private Project project;

    public Manager(String name, String cpf, int age, Sex sex, String email, String phone,
                   double salary, Benefits[] benefits, String departament, Project project) {
        super(name, cpf, age, sex, email, phone, salary, benefits);
        this.departament = departament;
        this.project = project;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
