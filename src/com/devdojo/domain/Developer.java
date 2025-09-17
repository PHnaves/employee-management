package com.devdojo.domain;

import com.devdojo.domain.enums.Developer.ExperienceDeveloper;
import com.devdojo.domain.enums.Developer.LanguagesDeveloper;
import com.devdojo.domain.enums.Developer.Specialty;

public class Developer extends Employee {
    private Specialty specialty;
    private LanguagesDeveloper[] languagesDevelopers;
    private ExperienceDeveloper experienceDeveloper;

    {
        if (this.experienceDeveloper.equals(ExperienceDeveloper.JUNIOR)) {
            double salary = this.getSalary() + 500;
            this.setSalary(salary);
        } else if (this.experienceDeveloper.equals(ExperienceDeveloper.SENIOR)) {
            double salary = this.getSalary() + 1000;
            this.setSalary(salary);
        } else if (this.experienceDeveloper.equals(ExperienceDeveloper.PLENO)) {
            double salary = this.getSalary() + 1500;
            this.setSalary(salary);
        }
    }

    public Developer(String name, String cpf, int age,
                     Sex sex, String phone, double salary,
                     Specialty specialty, LanguagesDeveloper[] languagesDeveloper,
                     ExperienceDeveloper experienceDeveloper) {
        super(name, cpf, age, sex, phone, salary);
        this.specialty = specialty;
        this.languagesDevelopers = languagesDeveloper;
        this.experienceDeveloper = experienceDeveloper;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public LanguagesDeveloper[] getLanguagesDevelopers() {
        return languagesDevelopers;
    }

    public void setLanguagesDevelopers(LanguagesDeveloper[] languagesDevelopers) {
        this.languagesDevelopers = languagesDevelopers;
    }

    public ExperienceDeveloper getExperienceDeveloper() {
        return experienceDeveloper;
    }

    public void setExperienceDeveloper(ExperienceDeveloper experienceDeveloper) {
        this.experienceDeveloper = experienceDeveloper;
    }
}
