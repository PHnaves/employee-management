package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Project;
import com.devdojo.domain.enums.Developer.DeveloperLanguages;
import com.devdojo.service.DeveloperService;
import com.devdojo.service.EmployeeService;

public class DeveloperServiceImpl implements DeveloperService {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void technicalInformations(Developer developer) {
        System.out.println("Informacoes tecnicas");
        System.out.println("Nivel de experiencia: " + developer.getDeveloperExperience());
        System.out.println("Especialidade: " + developer.getSDeveloperSpecialty());
        System.out.println("Linguagens utilizadas");
        for (DeveloperLanguages developerLanguages : developer.getDeveloperLanguages()) {
            System.out.println(developerLanguages.getLanguages());
        }
    }

    @Override
    public void project(Developer developer) {
        System.out.println("Projeto atual");
        System.out.println("Titulo do projeto" + developer.getProject().getTitle());
        System.out.println("Descricao");
        System.out.println(developer.getProject().getDescription());
        if (developer.getProject().getDevelopers() != null ) {
            System.out.println("Equipe");
            for (Developer developer1 : developer.getProject().getDevelopers()) {
                System.out.println(developer1.getName());
            }
        }else {
            System.out.println("Projeto sem membros no momento");
        }

    }

    @Override
    public void calculatorSalaryBenefits(Employee employee) {
        employeeService.calculatorSalaryBenefits(employee);
    }

    @Override
    public void generateReport(Employee employee) {
        employeeService.generateReport(employee);
    }

    @Override
    public void benefits(Employee employee) {
        employeeService.benefits(employee);
    }
}
