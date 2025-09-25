package com.devdojo.domain.enums.Developer;

public enum DeveloperLanguages {
    JAVA("Java, puro suco do milho tang"),
    PHP("PHP monstro"),
    JAVA_SCRIPT("JavaScript pepa"),
    COBOL("COBOL"),
    GOLANG("GOLANG"),
    CSHARP("C#"),
    TYPE_SCRIPT("TypeScript pepa deluxe"),
    SWIFT("Swift");

    private String languages;

    DeveloperLanguages(String languages ) {
        this.languages = languages;
    }

    public String getLanguages() {
        return languages;
    }
}
