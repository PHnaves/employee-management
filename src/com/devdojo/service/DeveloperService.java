package com.devdojo.service;

import com.devdojo.domain.Developer;

public interface DeveloperService extends EmployeeService {
    void createDeveloper();
    void showDevelopers();
    void readDeveloper(int id);
    void updateDeveloper(int id);
    void updateTechnicalInformations(int id);
    void deleteDeveloper(int id);
    void technicalInformations(Developer developer);
    void project(Developer developer);
}
