package com.devdojo.storage;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Manager;
import com.devdojo.domain.Project;

import java.util.Scanner;

public class DataStorage {
    public static Scanner scanner = new Scanner(System.in);
    public static Developer[] developersRegisters = new Developer[]{};
    public static Manager[] managersRegisters = new Manager[]{};
    public static Project[] projectsRegistered = new Project[]{};
}
