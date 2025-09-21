package com.devdojo.service;

import com.devdojo.domain.Employee;
import com.devdojo.domain.Project;

public interface EmployeeService {
    void calculatorSalaryBenefits(Employee employee);
    void generateReport(Employee employee);
    void benefits(Employee employee);
}
