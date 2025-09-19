package com.devdojo.domain.enums;

import com.devdojo.domain.Employee;

public enum Benefits {
    TRANSPORTATION_VOUCHER {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.06;
            System.out.println("Desconto de 6% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    MEAL_VOUCHER {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.02;
            System.out.println("Desconto de 2% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    FOOD_VOUCHER {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.02;
            System.out.println("Desconto de 2% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    HEALTH_INSURANCE {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.05;
            System.out.println("Desconto de 5% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    DENTAL_PLAN {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.01;
            System.out.println("Desconto de 1% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    LIFE_INSURANCE {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = employee.getSalary() * 0.01;
            System.out.println("Desconto de 1% em cima do seu salario!");
            System.out.println("Valor do desconto: " + salaryDescont);
            return salaryDescont;
        }
    },
    DAY_OFF_BIRTHDAY {
        @Override
        public double descontBenefits(Employee employee) {
            double salaryDescont = 0.0; // Sem desconto
            System.out.println("Sem desconto! Benefício 100% oferecido pela empresa.");
            return salaryDescont;
        }
    };


    public abstract double descontBenefits(Employee employee);
}
