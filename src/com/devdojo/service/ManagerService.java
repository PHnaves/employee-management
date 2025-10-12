package com.devdojo.service;

import com.devdojo.domain.Manager;

public interface ManagerService extends EmployeeService{
    void createManager();
    void showManager();
    void readManager(int id);
    void updateManager(int id);
    void updateTechnicalInformations(int id);
    void deleteManager(int id);
    void managerTeam(int id);
    void technicalInformations(Manager manager);
}
