package com.devdojo.service.impl;

import com.devdojo.domain.Employee;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void calculatorSalaryBenefits(Employee employee) {
        EmployeeBenefits[] empBenefits = employee.getBenefits();
        if (empBenefits == null || empBenefits.length < 1){
            System.out.println("Voce nao possui nenhum beneficio");
            System.out.println("Salario final: " + employee.getSalary());
        } else {
            double totalDescont = 0.0;
            int quantityBenefits = 0;
            for (EmployeeBenefits employeeBenefits : empBenefits) {
                double descont = employeeBenefits.descontBenefits(employee);
                totalDescont += descont;
                quantityBenefits++;
            }

            double salaryFinal = employee.getSalary() - totalDescont;
            System.out.println("Voce possui " + quantityBenefits + " beneficios ativos");
            System.out.println(String.format("Total do desconto: %.2f", totalDescont));
            System.out.println(String.format("Salario final: %.2f", salaryFinal));

        }
    }

    @Override
    public void generateReport(Employee employee) {
        System.out.println("Informacoes pessoais");
        System.out.println("Nome: " + employee.getName());
        System.out.println("CPF: " + employee.getCpf());
        System.out.println("Idade: " + employee.getAge());
        System.out.println("Sexo: " + employee.getSex());
    }

    @Override
    public void benefits(Employee employee) {
        if (employee.getBenefits() == null || employee.getBenefits().length < 1) {
            System.out.println("Voce nao possui nenhum beneficio");
        } else {
            System.out.println("Beneficios");
            for (EmployeeBenefits employeeBenefits : employee.getBenefits()){
                System.out.println();
                for (String benefitsEmployee : employeeBenefits.getBenefits()){
                    System.out.println(benefitsEmployee);
                }
            }
        }
    }
}
