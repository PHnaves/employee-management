package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.service.DeveloperService;
import com.devdojo.service.EmployeeService;

public class DeveloperServiceImpl implements DeveloperService {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void technicalInformations(Developer developer) {

    }

    @Override
    public void project(Developer developer) {

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
