package com.devdojo.domain.enums.Developer;

public enum DeveloperSpecialty {
    BACKEND("BackEnd"),
    FRONTEND("FrontEnd"),
    FULLSTACK("FullStack"),
    MOBILE("Mobile"),
    DEVOPS("DevOps"),
    DATA("Data");

    private String specialty;

    DeveloperSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return this.specialty;
    }

}
