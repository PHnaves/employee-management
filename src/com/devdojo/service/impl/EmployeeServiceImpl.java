package com.devdojo.service.impl;

import com.devdojo.domain.Employee;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void calculatorSalaryBenefits(Employee employee) {
        EmployeeBenefits[] empBenefits = employee.getBenefits();
        if (empBenefits != null && empBenefits.length > 0) {
            double totalDescont = 0.0;
            for (EmployeeBenefits employeeBenefits : empBenefits) {
                totalDescont += employeeBenefits.descontBenefits(employee);
            }

            double salaryFinal = employee.getSalary() - totalDescont;

            System.out.println("\n=========================================");
            System.out.println("💰 CÁLCULO DE SALÁRIO E BENEFÍCIOS 💰");
            System.out.println("=========================================");
            System.out.printf("Total de desconto: R$ %.2f%n", totalDescont);
            System.out.printf("Salário final    : R$ %.2f%n", salaryFinal);
            System.out.println("=========================================\n");
        } else {
            System.out.println("\n⚠ O funcionário não possui benefícios para calcular.\n");
        }
    }

    @Override
    public void generateReport(Employee employee) {
        System.out.printf("ID   : %d%n", employee.getId());
        System.out.printf("Nome : %s%n", employee.getName());
        System.out.printf("CPF  : %s%n", employee.getCpf());
        System.out.printf("Idade: %d%n", employee.getAge());
        System.out.printf("Sexo : %s%n", employee.getSex());
        System.out.println("=========================================\n");
    }

    @Override
    public void benefits(Employee employee) {
        EmployeeBenefits[] empBenefits = employee.getBenefits();
        System.out.println("\n=========================================");
        System.out.println("🎁 BENEFÍCIOS DO FUNCIONÁRIO 🎁");
        System.out.println("=========================================");

        if (empBenefits != null && empBenefits.length > 0) {
            for (int i = 0; i < empBenefits.length; i++) {
                System.out.println((i + 1) + "º Benefício:");
                for (String benefit : empBenefits[i].getBenefits()) {
                    System.out.println(" - " + benefit);
                }
                System.out.println("-----------------------------------------");
            }
            calculatorSalaryBenefits(employee);
        } else {
            System.out.println("⚠ Este funcionário não possui benefícios atualmente.");
        }

        System.out.println("=========================================\n");
    }
}
