package com.devdojo.domain.enums.manager;

public enum ManagerDepartament {
    HR("Recursos Humanos"),
    IT("Tecnologia da Informacao"),
    FINANCE("Financas"),
    SALES("Vendas"),
    MARKETING("Marketing"),
    OPERATIONS("Operacoes");

    private String departament;

    ManagerDepartament(String departament) {
        this.departament = departament;
    }

    public String getDepartament(){
        return this.departament;
    }
}
