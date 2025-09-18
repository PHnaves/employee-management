package com.devdojo.domain;

import com.devdojo.domain.enums.Benefits;

public abstract class Employee extends Person {
    private String email,phone;
    private double salary;
    private Benefits[] benefits;

    static {
        System.out.println("Primeiro funcionario cadastrado!");
    }

    public Employee(String name, String cpf, int age, Sex sex, String phone, String email, double salary, Benefits[] benefits) {
        super(name, cpf, age, sex);
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.benefits = benefits;
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

    public Benefits[] getBenefits() {
        return this.benefits;
    }

    public void setBenefits(Benefits[] benefits) {
        this.benefits = benefits;
    }
}
