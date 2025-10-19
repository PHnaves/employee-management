package com.devdojo.domain.enums.manager;

public enum ManagerResponsibility {
    TEAM_MANAGEMENT("Gestao de equipe"),
    BUDGET_APPROVAL("Aprovacao de orcamento"),
    STRATEGIC_PLANNING("Planejamento estrategico"),
    PERFORMANCE_REVIEW("Avaliacao de desempenho"),
    PROJECT_SUPERVISION("Supervisao de projetos");

    private String responsability;

    ManagerResponsibility(String managerResponsability) {
        this.responsability = managerResponsability;
    }

    public String getResponsability(){
        return this.responsability;
    }
}
