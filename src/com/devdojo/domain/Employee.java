package com.devdojo.domain;

import com.devdojo.domain.enums.EmployeeBenefits;

public abstract class Employee extends Person {
    private int id;
    private String email,phone;
    private double salary;
    private EmployeeBenefits[] benefits;

    static {
        System.out.println("Primeiro funcionario cadastrado!");
    }

    public Employee(int id, String name, String cpf, int age, Sex sex, String phone, String email, double salary, EmployeeBenefits[] benefits) {
        super(name, cpf, age, sex);
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.benefits = benefits;
    }

    public Employee(){

    }

    public int getId(){
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeBenefits[] getBenefits() {
        return this.benefits;
    }

    public void setBenefits(EmployeeBenefits[] benefits) {
        this.benefits = benefits;
    }
}
