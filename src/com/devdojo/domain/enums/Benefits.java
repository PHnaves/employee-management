package com.devdojo.domain.enums;

import com.devdojo.domain.Employee;

public enum Benefits {
    TRANSPORTATION_VOUCHER {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.06;
            System.out.println("Desconto de 6% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    MEAL_VOUCHER {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.02;
            System.out.println("Desconto de 2% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    FOOD_VOUCHER {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.02;
            System.out.println("Desconto de 2% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    HEALTH_INSURANCE {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.05;
            System.out.println("Desconto de 5% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    DENTAL_PLAN {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.01;
            System.out.println("Desconto de 1% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    LIFE_INSURANCE {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.01;
            System.out.println("Desconto de 1% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
        }
    },
    DAY_OFF_BIRTHDAY {
        @Override
        public void descontBenefits(Employee employee) {
            double salaryDescont = 0.0; // Sem desconto
            System.out.println("Sem desconto! Benefício 100% oferecido pela empresa.");
        }
    };


    public abstract void descontBenefits(Employee employee);
}
