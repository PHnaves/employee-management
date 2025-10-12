package com.devdojo.domain.enums.Developer;

public enum DeveloperExperience {
    JUNIOR("Junior"),
    SENIOR("Senior"),
    PLENO("Pleno");

    private String experience;

    DeveloperExperience(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }
}
