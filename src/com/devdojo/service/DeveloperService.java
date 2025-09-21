package com.devdojo.service;

import com.devdojo.domain.Developer;

public interface DeveloperService extends EmployeeService {
    void technicalInformations(Developer developer);
    void project(Developer developer);
}
