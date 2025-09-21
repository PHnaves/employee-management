package com.devdojo.domain;

import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.domain.enums.manager.ManagerDepartament;

public class Manager extends Employee {
    private ManagerDepartament managerDepartament;
    private Employee[] employees;

    public Manager(int id, String name, String cpf, int age, Sex sex, String email, String phone,
                   double salary, EmployeeBenefits[] benefits, ManagerDepartament managerDepartament, Employee[] employees) {
        super(id, name, cpf, age, sex, email, phone, salary, benefits);
        this.managerDepartament = managerDepartament;
    }

    public ManagerDepartament getDepartamentManager() {
        return managerDepartament;
    }

    public void setDepartamentManager(ManagerDepartament managerDepartament) {
        this.managerDepartament = managerDepartament;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
