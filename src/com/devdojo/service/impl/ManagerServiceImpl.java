package com.devdojo.service.impl;

import com.devdojo.domain.Employee;
import com.devdojo.domain.Manager;
import com.devdojo.domain.Person;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.domain.enums.manager.ManagerDepartament;
import com.devdojo.domain.enums.manager.ManagerResponsibility;
import com.devdojo.service.ManagerService;

import java.util.Scanner;

public class ManagerServiceImpl implements ManagerService {
    public static final Scanner scanner = new Scanner(System.in);
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();
    public static Employee[] managersRegisters = new Employee[]{};

    @Override
    public void createManager() {
        System.out.println("-----Informacoes pessoais-----");
        System.out.println("ID do usuario: ");
        int managerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome do funcionario");
        String managerName = scanner.nextLine();

        System.out.println("Cpf do funcionario: ");
        String managerCpf = scanner.nextLine();

        System.out.println("Idade do funcionario: ");
        int managerAge = scanner.nextInt();
        scanner.nextLine();

        Person.Sex managerSex = null;
        while(managerSex == null) {
            System.out.println("Sexo - MASCULINO OU FEMININO:");
            String inputSex = scanner.nextLine().trim(); // remove espaços extras
            if (inputSex.equalsIgnoreCase("masculino")) {
                managerSex = Person.Sex.MASCULINO;
            } else if (inputSex.equalsIgnoreCase("feminino")) {
                managerSex = Person.Sex.FEMININO;
            } else {
                System.out.println("Sexo inválido, digite novamente.");
            }
        }

        System.out.println("Email do funcionario: ");
        String managerEmail = scanner.nextLine();

        System.out.println("Telefone do funcionario: ");
        String managerPhone = scanner.nextLine();

        System.out.println("-----Informacoes tecnicas-----");
        System.out.println("Salario do funcionario: ");
        double managerSalary = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Departamentos disponiveis");
        for (ManagerDepartament managerDepartament : ManagerDepartament.values()) {
            System.out.println(managerDepartament.getDepartament());
        }

        ManagerDepartament managerDepartament = null;
        while(true) {
            System.out.print("Digite respectivamente seu departamento: ");
            String input = scanner.nextLine();

            for (ManagerDepartament md : ManagerDepartament.values()) {
                if (md.getDepartament().equalsIgnoreCase(input)) {
                    managerDepartament = md;
                    break;
                }
            }

            if (managerDepartament != null) {
                break;
            } else {
                System.out.println("Departamento inválido. Por favor, digite um dos valores da lista.");
            }
        }

        System.out.println("responsabilidades disponiveis");
        for (ManagerResponsibility managerResponsability : ManagerResponsibility.values()) {
            System.out.println(managerResponsability.getResponsability());
        }

        ManagerResponsibility managerResponsibility = null;
        while(true) {
            System.out.print("Digite respectivamente sua responsabilidade: ");
            String input = scanner.nextLine();


            for (ManagerResponsibility mr : ManagerResponsibility.values()) {
                if (mr.getResponsability().equalsIgnoreCase(input)) {
                    managerResponsibility = mr;
                    break;
                }
            }

            if (managerResponsibility != null) {
                break;
            } else {
                System.out.println("Responsabilidade inválida. Por favor, digite um dos valores da lista.");
            }
        }

        System.out.println("Beneficios disponivies");
        for (EmployeeBenefits employeeBenefits : EmployeeBenefits.values()) {
            System.out.println(employeeBenefits.getBenefits()[0]);
        }

        System.out.println("Quantos beneficios quer adicionar? ");
        int benefitsQuantity = scanner.nextInt();
        scanner.nextLine();
        EmployeeBenefits[] benefitsSelect = new EmployeeBenefits[benefitsQuantity];

        for (int i = 0; i < benefitsQuantity; i++) {
            boolean valid = false;

            while (!valid) {
                System.out.print((i + 1) + "ª beneficio: ");
                String input = scanner.nextLine().trim();

                for (EmployeeBenefits eb : EmployeeBenefits.values()) {
                    if (eb.getBenefits()[0].equalsIgnoreCase(input)) {
                        benefitsSelect[i] = eb;
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println("beneficio inválido, digite novamente!");
                }
            }
        }

        Employee manager = new Manager(
                managerId,
                managerName,
                managerCpf,
                managerAge,
                managerSex,
                managerEmail,
                managerPhone,
                managerSalary,
                managerDepartament,
                managerResponsibility,
                benefitsSelect
        );

        Employee[] temp = new Employee[managersRegisters.length + 1];

        for (int i = 0; i < managersRegisters.length; i++) {
            temp[i] = managersRegisters[i];
        }

        temp[managersRegisters.length] = manager;

        managersRegisters = temp;
    }

    @Override
    public void showManager() {
        System.out.println("Gerentes cadastrados");
        for (Employee manager : managersRegisters) {
            System.out.println("ID = " + manager.getId());
            System.out.println("Nome = " + manager.getName());
            System.out.println("-------------------------------");
        }
    }

    @Override
    public void readManager(int id) {
        Manager targetManager = null;
        for (Employee emp : managersRegisters) {
            if (emp instanceof Manager && emp.getId() == id) {
                targetManager = (Manager) emp;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("Gerente com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n------ RELATORIO GERENTE: " + targetManager.getName() + " ------");
        this.generateReport(targetManager);
        System.out.println();
        this.technicalInformations(targetManager);
        System.out.println();
        this.benefits(targetManager);
    }

    @Override
    public void updateManager(int id) {
        Manager targetManager = null;
        for (Employee emp : managersRegisters) {
            if (emp instanceof Manager && emp.getId() == id) {
                targetManager = (Manager) emp;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("Gerente com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMACOES PESSOAIS GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Nome");
            System.out.println("2 - CPF");
            System.out.println("3 - Idade");
            System.out.println("4 - Sexo");
            System.out.println("5 - voltar");
            System.out.println("Digite a opcao correspondente: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Digite o novo nome: ");
                    String newName = scanner.nextLine();
                    targetManager.setName(newName);
                    System.out.println("Nome editado com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Digite o novo CPF: ");
                    String newCpf = scanner.nextLine();
                    targetManager.setCpf(newCpf);
                    System.out.println("CPF editado com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Digite a nova idade: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    targetManager.setAge(newAge);
                    System.out.println("Idade editada com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 4:
                    boolean isCorrect = false;
                    while(!isCorrect) {
                        System.out.println("Digite o novo Sexo - MASCULINO OU FEMININO:");
                        String newSex = scanner.nextLine().trim(); // remove espaços extras
                        if (newSex.equalsIgnoreCase("masculino")) {
                            targetManager.setSex(Person.Sex.MASCULINO);
                            System.out.println("Sexo editado com sucesso!");
                            isCorrect = true;
                        } else if (newSex.equalsIgnoreCase("feminino")) {
                            targetManager.setSex(Person.Sex.FEMININO);
                            System.out.println("Sexo editado com sucesso!");
                            isCorrect = true;
                        } else {
                            System.out.println("Sexo inválido, digite novamente.");
                        }
                    }

                    System.out.println("Quer continuar editando? Sim ou Nao");
                    continueEdit = scanner.nextLine();
                    if (continueEdit.equalsIgnoreCase("nao")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 5:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Digite somente umas das opcoes!");
                    break;
            }
        }


    }

    @Override
    public void updateTechnicalInformations(int id) {
        Manager targetManager = null;
        for (Employee emp : managersRegisters) {
            if (emp instanceof Manager && emp.getId() == id) {
                targetManager = (Manager) emp;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("Gerente com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMACOES TECNICAS GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Departamento");
            System.out.println("2 - Responsabilidade");
            System.out.println("3 - Sair");
            System.out.println("Digite a opcao correspondente: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Departamentos disponiveis");
                    for (ManagerDepartament managerDepartament : ManagerDepartament.values()) {
                        System.out.println(managerDepartament.getDepartament());
                    }

                    ManagerDepartament newDepartament = null;
                    while(true) {
                        System.out.print("Digite respectivamente seu novo departamento: ");
                        String input = scanner.nextLine();

                        for (ManagerDepartament md : ManagerDepartament.values()) {
                            if (md.getDepartament().equalsIgnoreCase(input)) {
                                newDepartament = md;
                                break;
                            }
                        }

                        if (newDepartament != null) {
                            targetManager.setManagerDepartament(newDepartament);
                            System.out.println("Departamento editado com sucesso!");
                            break;
                        } else {
                            System.out.println("Departamento inválido. Por favor, digite um dos valores da lista.");
                        }
                    }

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("responsabilidades disponiveis");
                    for (ManagerResponsibility managerResponsability : ManagerResponsibility.values()) {
                        System.out.println(managerResponsability.getResponsability());
                    }

                    ManagerResponsibility newResponsability = null;
                    while(true) {
                        System.out.print("Digite respectivamente sua nova responsabilidade: ");
                        String input = scanner.nextLine();


                        for (ManagerResponsibility mr : ManagerResponsibility.values()) {
                            if (mr.getResponsability().equalsIgnoreCase(input)) {
                                newResponsability = mr;
                                break;
                            }
                        }

                        if (newResponsability != null) {
                            targetManager.setManagerResponsibility(newResponsability);
                            System.out.println("Responsabilidade editada com sucesso!");
                            break;
                        } else {
                            System.out.println("Responsabilidade inválida. Por favor, digite um dos valores da lista.");
                        }
                    }

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Digite somente umas das opcoes!");
                    break;
            }
        }
    }

    @Override
    public void deleteManager(int id) {
        int indexToRemove = -1;

        for (int i = 0; i < managersRegisters.length; i++) {
            if (managersRegisters[i].getId() == id) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Gerente com ID " + id + " não encontrado.");
            return;
        }

        Employee[] temp = new Employee[managersRegisters.length - 1];

        for (int i = 0, j = 0; i < managersRegisters.length; i++) {
            if (i != indexToRemove) {
                temp[j++] = managersRegisters[i];
            }
        }

        managersRegisters = temp;

        System.out.println("Gerente com ID " + id + " removido com sucesso!");
    }

    @Override
    public void managerTeam(int id) {
        Manager targetManager = null;
        for (Employee emp : managersRegisters) {
            if (emp instanceof Manager && emp.getId() == id) {
                targetManager = (Manager) emp;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("Gerente com ID " + id + " não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n------ GERENCIAR EQUIPE DO GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Ver equipe atual");
            System.out.println("2 - Adicionar developer à equipe");
            System.out.println("3 - Remover developer da equipe");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("------ EQUIPE ATUAL ------");
                    Employee[] currentTeam = targetManager.getEmployees();
                    if (currentTeam != null && currentTeam.length > 0) {
                        for (Employee teamMember : currentTeam) {
                            System.out.println("ID   = " + teamMember.getId());
                            System.out.println("Nome = " + teamMember.getName());
                            System.out.println("--------------------------");
                        }
                    } else {
                        System.out.println("Nenhum membro na equipe.");
                    }
                    break;
                case 2:
                    System.out.println("------ ADICIONAR DESENVOLVEDOR ------");
                    if (developerService.getDevelopersRegisters().length == 0) {
                        System.out.println("Nenhum desenvolvedor cadastrado no sistema para adicionar.");
                        break;
                    }

                    System.out.println("Desenvolvedores disponíveis:");
                    for (Employee employeeAvailable : developerService.getDevelopersRegisters()) {
                        System.out.println("ID = " + employeeAvailable.getId() + ", Nome = " + employeeAvailable.getName());
                    }

                    System.out.print("Digite o ID do novo membro da equipe: ");
                    int employeeIdToAdd = scanner.nextInt();
                    scanner.nextLine();

                    Employee devToAdd = null;
                    for (Employee dev : developerService.getDevelopersRegisters()) {
                        if (dev.getId() == employeeIdToAdd) {
                            devToAdd = dev;
                            break;
                        }
                    }

                    if (devToAdd == null) {
                        System.out.println("ERRO: Desenvolvedor com ID informado não encontrado.");
                    } else {
                        Employee[] oldTeam = targetManager.getEmployees() == null ? new Employee[0] : targetManager.getEmployees();

                        boolean alreadyInTeam = false;
                        for(Employee member : oldTeam) {
                            if(member.getId() == devToAdd.getId()){
                                alreadyInTeam = true;
                                break;
                            }
                        }

                        if(alreadyInTeam) {
                            System.out.println("ERRO: Este desenvolvedor já faz parte da equipe.");
                        } else {
                            Employee[] newTeam = new Employee[oldTeam.length + 1];
                            for (int i = 0; i < oldTeam.length; i++) {
                                newTeam[i] = oldTeam[i];
                            }
                            newTeam[oldTeam.length] = devToAdd;

                            targetManager.setEmployees(newTeam);
                            System.out.println("Novo membro adicionado à equipe com sucesso!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("------ REMOVER DESENVOLVEDOR ------");
                    Employee[] teamToRemoveFrom = targetManager.getEmployees();
                    if (teamToRemoveFrom == null || teamToRemoveFrom.length == 0) {
                        System.out.println("A equipe já está vazia. Nenhum membro para remover.");
                        break;
                    }

                    System.out.println("Membros da equipe atual:");
                    for (Employee teamMember : teamToRemoveFrom) {
                        System.out.println("ID = " + teamMember.getId() + ", Nome = " + teamMember.getName());
                    }
                    System.out.print("Digite o id do membro que desejas remover: ");
                    int employeeIdToRemove = scanner.nextInt();
                    scanner.nextLine();

                    int indexToRemove = -1;
                    for (int i = 0; i < teamToRemoveFrom.length; i++) {
                        if (teamToRemoveFrom[i].getId() == employeeIdToRemove) {
                            indexToRemove = i;
                            break;
                        }
                    }

                    if (indexToRemove == -1) {
                        System.out.println("ERRO: Membro com o ID informado não encontrado na equipe.");
                    } else {
                        Employee[] newTeam = new Employee[teamToRemoveFrom.length - 1];
                        for (int i = 0, j = 0; i < teamToRemoveFrom.length; i++) {
                            if (i != indexToRemove) {
                                newTeam[j++] = teamToRemoveFrom[i];
                            }
                        }
                        targetManager.setEmployees(newTeam);
                        System.out.println("Membro removido da equipe com sucesso!");
                    }
                    break;

                case 4:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    @Override
    public void technicalInformations(Manager manager) {
    System.out.println("Informacoes tecnicas");
        System.out.println("Departamento responsavel");
        System.out.println(manager.getManagerDepartament());
        System.out.println();
        System.out.println("Responsabilidade");
        System.out.println(manager.getManagerResponsibility());
        System.out.println();
        if (manager.getEmployees() != null && manager.getEmployees().length > 0) {
            System.out.println("Equipe");
            for (Employee employee : manager.getEmployees()) {
                System.out.println(employee.getId());
                System.out.println(employee.getName());
                System.out.println();
            }
        }
    }

    @Override
    public void calculatorSalaryBenefits(Employee employee) {
        employeeService.calculatorSalaryBenefits(employee);
    }

    @Override
    public void generateReport(Employee employee) {
        employeeService.generateReport(employee);
    }

    @Override
    public void benefits(Employee employee) {
        employeeService.benefits(employee);
    }

    public Employee[] getManagersRegisters() {
        return managersRegisters;
    }
}
