package com.devdojo.domain;

public abstract class Employee extends Person {
    private String phone;
    private double salary;

    static {
        System.out.println("Primeiro funcionario cadastrado!");
    }

    public Employee(String name, String cpf, int age, Sex sex, String phone, double salary) {
        super(name, cpf, age, sex);
        this.phone = phone;
        this.salary = salary;
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
}
