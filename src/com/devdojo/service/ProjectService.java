package com.devdojo.service;

public interface ProjectService {
    void createProject();
    void showProjects();
    void readProject(String title);
    void updateProject(String title);
    void deleteProject(String title);
    void managerTeam(String title);
}
