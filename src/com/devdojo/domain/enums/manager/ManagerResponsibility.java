package com.devdojo.domain.enums.manager;

public enum ManagerResponsibility {
    TEAM_MANAGEMENT("Gestao de equipe"),
    BUDGET_APPROVAL("Aprovação de orçamento"),
    STRATEGIC_PLANNING("Planejamento estratégico"),
    PERFORMANCE_REVIEW("Avaliação de desempenho"),
    PROJECT_SUPERVISION("Supervisão de projetos");

    private String responsability;

    ManagerResponsibility(String managerResponsability) {
        this.responsability = managerResponsability;
    }

    public String getResponsability(){
        return this.responsability;
    }
}
