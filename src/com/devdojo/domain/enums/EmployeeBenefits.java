package com.devdojo.domain.enums;

import com.devdojo.domain.Employee;

public enum EmployeeBenefits {
    TRANSPORTATION_VOUCHER(new String[]{"Vale transporte", "6% de desconto em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.06;
        }
    },
    MEAL_VOUCHER(new String[]{"Vale refeicao", "2% de desconto em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.02;
        }
    },
    FOOD_VOUCHER(new String[]{"Vale alimentacao", "2% em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.02;
        }
    },
    HEALTH_INSURANCE(new String[]{"Plano de saude", "5% em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.05;
        }
    },
    DENTAL_PLAN(new String[]{"Plano odontologico", "1% em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.01;
        }
    },
    LIFE_INSURANCE(new String[]{"Seguro de vida", "2% em cima do salario"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return employee.getSalary() * 0.02;
        }
    },
    DAY_OFF_BIRTHDAY(new String[]{"Folga aniversario", "sem desconto"}) {
        @Override
        public double descontBenefits(Employee employee) {
            return 0.0;
        }
    };

    private String[] benefits;

    EmployeeBenefits(String[] benefits){
        this.benefits = benefits;
    }


    public abstract double descontBenefits(Employee employee);

    public String[] getBenefits(){
        return this.benefits;
    }
}
