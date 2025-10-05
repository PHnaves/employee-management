package com.devdojo.service.impl;

import com.devdojo.domain.Employee;
import com.devdojo.domain.Manager;
import com.devdojo.service.ManagerService;

import java.util.Scanner;

public class ManagerServiceImpl implements ManagerService {
    static final Scanner scanner = new Scanner(System.in);
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private Employee[] managersRegistereds = new Employee[]{};

    @Override
    public void createManager() {

    }

    @Override
    public void showManager() {

    }

    @Override
    public void readManager(int id) {

    }

    @Override
    public void updateManager(int id) {

    }

    @Override
    public void updateTechnicalInformations(int id) {

    }

    @Override
    public void employeesTeam(Manager manager) {

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
