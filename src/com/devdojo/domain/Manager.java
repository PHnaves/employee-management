package com.devdojo.domain;

import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.domain.enums.manager.ManagerDepartament;
import com.devdojo.domain.enums.manager.ManagerResponsibility;

public class Manager extends Employee {
    private ManagerDepartament managerDepartament;
    private ManagerResponsibility managerResponsibility;
    private Employee[] employees;

    public Manager(int id, String name, String cpf, int age, Sex sex, String email, String phone,
                   double salary, ManagerDepartament managerDepartament, ManagerResponsibility managerResponsibility, EmployeeBenefits[] benefits) {
        super(id, name, cpf, age, sex, email, phone, salary, benefits);
        this.managerDepartament = managerDepartament;
        this.managerResponsibility = managerResponsibility;
    }

    public Manager(int id, String name, String cpf, int age, Sex sex, String email, String phone,
                   double salary, EmployeeBenefits[] benefits, ManagerDepartament managerDepartament,
                   ManagerResponsibility managerResponsibility, Employee[] employees) {
        super(id, name, cpf, age, sex, email, phone, salary, benefits);
        this.managerDepartament = managerDepartament;
        this.managerResponsibility = managerResponsibility;
        this.employees = employees;
    }

    public ManagerDepartament getManagerDepartament() {
        return managerDepartament;
    }

    public void setManagerDepartament(ManagerDepartament managerDepartament) {
        this.managerDepartament = managerDepartament;
    }

    public ManagerResponsibility getManagerResponsibility() {
        return managerResponsibility;
    }

    public void setManagerResponsibility(ManagerResponsibility managerResponsibility) {
        this.managerResponsibility = managerResponsibility;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}
