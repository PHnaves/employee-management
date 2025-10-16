package com.devdojo.service;

public interface ProjectService {
    void createProject();
    void showProject();
    void readProject(String title);
    void updateProject(String title);
    void deleteProject(String title);
}
