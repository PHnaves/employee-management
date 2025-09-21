package com.devdojo.domain;

import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.domain.enums.Developer.DeveloperExperience;
import com.devdojo.domain.enums.Developer.DeveloperLanguages;
import com.devdojo.domain.enums.Developer.DeveloperSpecialty;

public class Developer extends Employee {
    private DeveloperSpecialty developerSpecialty;
    private DeveloperLanguages[] developerLanguages;
    private DeveloperExperience developerExperience;
    private Project project;

    public Developer(int id, String name, String cpf, int age,
                     Sex sex, String email, String phone, double salary, EmployeeBenefits[] benefits,
                     DeveloperSpecialty developerSpecialty, DeveloperLanguages[] developerLanguages,
                     DeveloperExperience developerExperience, Project project) {
        super(id, name, cpf, age, sex, email, phone, salary, benefits);
        this.developerSpecialty = developerSpecialty;
        this.developerLanguages = developerLanguages;
        this.developerExperience = developerExperience;
        this.project = project;
    }

    public Developer(){

    }

    public DeveloperSpecialty getSpecialty() {
        return developerSpecialty;
    }

    public void setSpecialty(DeveloperSpecialty developerSpecialty) {
        this.developerSpecialty = developerSpecialty;
    }

    public DeveloperLanguages[] getLanguagesDevelopers() {
        return developerLanguages;
    }

    public void setLanguagesDevelopers(DeveloperLanguages[] developerLanguages) {
        this.developerLanguages = developerLanguages;
    }

    public DeveloperExperience getExperienceDeveloper() {
        return developerExperience;
    }

    public void setExperienceDeveloper(DeveloperExperience developerExperience) {
        this.developerExperience = developerExperience;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
