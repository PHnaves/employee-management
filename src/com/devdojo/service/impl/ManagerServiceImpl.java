package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Manager;
import com.devdojo.domain.Person;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.domain.enums.manager.ManagerDepartament;
import com.devdojo.domain.enums.manager.ManagerResponsibility;
import com.devdojo.service.ManagerService;
import com.devdojo.storage.DataStorage;

public class ManagerServiceImpl implements ManagerService {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public void createManager() {
        System.out.println("\n=========================================");
        System.out.println("📝 INFORMACOES PESSOAIS DO GERENTE 📝");
        System.out.println("=========================================");

        System.out.print("ID do gerente: ");
        int managerId = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        System.out.print("Nome do gerente: ");
        String managerName = DataStorage.scanner.nextLine();

        System.out.print("CPF do gerente: ");
        String managerCpf = DataStorage.scanner.nextLine();

        System.out.print("Idade do gerente: ");
        int managerAge = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        Person.Sex managerSex = null;
        while(managerSex == null) {
            System.out.print("Sexo (MASCULINO/FEMININO): ");
            String inputSex = DataStorage.scanner.nextLine().trim();
            if (inputSex.equalsIgnoreCase("masculino")) {
                managerSex = Person.Sex.MASCULINO;
            } else if (inputSex.equalsIgnoreCase("feminino")) {
                managerSex = Person.Sex.FEMININO;
            } else {
                System.out.println("⚠ Sexo inválido. Digite novamente.");
            }
        }

        System.out.print("Email do gerente: ");
        String managerEmail = DataStorage.scanner.nextLine();

        System.out.print("Telefone do gerente: ");
        String managerPhone = DataStorage.scanner.nextLine();

        System.out.println("\n=========================================");
        System.out.println("💼 INFORMACOES TECNICAS DO GERENTE 💼");
        System.out.println("=========================================");

        System.out.print("Salário do gerente: R$ ");
        double managerSalary = DataStorage.scanner.nextDouble();
        DataStorage.scanner.nextLine();

        System.out.println("\nDepartamentos disponíveis:");
        for (ManagerDepartament md : ManagerDepartament.values()) {
            System.out.println(" - " + md.getDepartament());
        }

        ManagerDepartament managerDepartament = null;
        while(true) {
            System.out.print("Digite seu departamento: ");
            String input = DataStorage.scanner.nextLine();

            for (ManagerDepartament md : ManagerDepartament.values()) {
                if (md.getDepartament().equalsIgnoreCase(input)) {
                    managerDepartament = md;
                    break;
                }
            }

            if (managerDepartament != null) break;
            System.out.println("⚠ Departamento inválido. Tente novamente.");
        }

        System.out.println("\nResponsabilidades disponíveis:");
        for (ManagerResponsibility mr : ManagerResponsibility.values()) {
            System.out.println(" - " + mr.getResponsability());
        }

        ManagerResponsibility managerResponsibility = null;
        while(true) {
            System.out.print("Digite sua responsabilidade: ");
            String input = DataStorage.scanner.nextLine();

            for (ManagerResponsibility mr : ManagerResponsibility.values()) {
                if (mr.getResponsability().equalsIgnoreCase(input)) {
                    managerResponsibility = mr;
                    break;
                }
            }

            if (managerResponsibility != null) break;
            System.out.println("⚠ Responsabilidade inválida. Tente novamente.");
        }

        System.out.println("\nBenefícios disponíveis:");
        for (EmployeeBenefits mb : EmployeeBenefits.values()) {
            System.out.println(" - " + mb.getBenefits()[0]);
        }

        System.out.print("Quantos benefícios deseja adicionar? ");
        int benefitsQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();
        EmployeeBenefits[] managerBenefits = new EmployeeBenefits[benefitsQuantity];

        for (int i = 0; i < benefitsQuantity; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print((i + 1) + "º benefício: ");
                String input = DataStorage.scanner.nextLine().trim();

                for (EmployeeBenefits mb : EmployeeBenefits.values()) {
                    if (mb.getBenefits()[0].equalsIgnoreCase(input)) {
                        managerBenefits[i] = mb;
                        valid = true;
                        break;
                    }
                }

                if (!valid) System.out.println("⚠ Benefício inválido. Tente novamente.");
            }
        }

        Manager manager = new Manager(
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
                managerBenefits
        );

        Manager[] newManagerRegisters = new Manager[DataStorage.managersRegisters.length + 1];
        for (int i = 0; i < DataStorage.managersRegisters.length; i++) {
            newManagerRegisters[i] = DataStorage.managersRegisters[i];
        }
        newManagerRegisters[DataStorage.managersRegisters.length] = manager;
        DataStorage.managersRegisters = newManagerRegisters;

        System.out.println("\n✅ Gerente cadastrado com sucesso!\n");
    }

    @Override
    public void showManager() {
        System.out.println("\n=========================================");
        System.out.println("👥 GERENTES CADASTRADOS 👥");
        System.out.println("=========================================");
        for (Manager manager : DataStorage.managersRegisters) {
            System.out.printf("ID   : %d%n", manager.getId());
            System.out.printf("Nome : %s%n", manager.getName());
            System.out.println("-----------------------------------------");
        }
        System.out.println();
    }

    @Override
    public void readManager(int id) {
        Manager targetManager = null;
        for (Manager manager : DataStorage.managersRegisters) {
            if (manager.getId() == id) {
                targetManager = manager;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("⚠ Gerente com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n=========================================");
        System.out.println("📄 RELATÓRIO DO GERENTE: " + targetManager.getName());
        System.out.println("=========================================");
        this.generateReport(targetManager);
        System.out.println();
        this.technicalInformations(targetManager);
        System.out.println();
        this.benefits(targetManager);
    }

    @Override
    public void updateManager(int id) {
        Manager targetManager = null;
        for (Manager manager : DataStorage.managersRegisters) {
            if (manager.getId() == id) {
                targetManager = manager;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("⚠ Gerente com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMAÇÕES PESSOAIS GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Nome");
            System.out.println("2 - CPF");
            System.out.println("3 - Idade");
            System.out.println("4 - Sexo");
            System.out.println("5 - Voltar");
            System.out.print("Digite a opção correspondente: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit;
            switch (option) {
                case 1 -> {
                    System.out.print("Digite o novo nome: ");
                    targetManager.setName(DataStorage.scanner.nextLine());
                    System.out.println("✅ Nome editado com sucesso!");
                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 2 -> {
                    System.out.print("Digite o novo CPF: ");
                    targetManager.setCpf(DataStorage.scanner.nextLine());
                    System.out.println("✅ CPF editado com sucesso!");
                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 3 -> {
                    System.out.print("Digite a nova idade: ");
                    targetManager.setAge(DataStorage.scanner.nextInt());
                    DataStorage.scanner.nextLine();
                    System.out.println("✅ Idade editada com sucesso!");
                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 4 -> {
                    boolean isCorrect = false;
                    while(!isCorrect) {
                        System.out.print("Digite o novo sexo (MASCULINO/FEMININO): ");
                        String newSex = DataStorage.scanner.nextLine().trim();
                        if (newSex.equalsIgnoreCase("masculino")) {
                            targetManager.setSex(Person.Sex.MASCULINO);
                            System.out.println("✅ Sexo editado com sucesso!");
                            isCorrect = true;
                        } else if (newSex.equalsIgnoreCase("feminino")) {
                            targetManager.setSex(Person.Sex.FEMININO);
                            System.out.println("✅ Sexo editado com sucesso!");
                            isCorrect = true;
                        } else {
                            System.out.println("⚠ Sexo inválido. Tente novamente.");
                        }
                    }
                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 5 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida. Tente novamente.");
            }
        }
    }

    @Override
    public void updateTechnicalInformations(int id) {
        Manager targetManager = null;
        for (Manager manager : DataStorage.managersRegisters) {
            if (manager.getId() == id) {
                targetManager = manager;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("⚠ Gerente com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMAÇÕES TÉCNICAS GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Departamento");
            System.out.println("2 - Responsabilidade");
            System.out.println("3 - Sair");
            System.out.print("Digite a opção correspondente: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit;
            switch (option) {
                case 1 -> {
                    System.out.println("Departamentos disponíveis:");
                    for (ManagerDepartament md : ManagerDepartament.values()) {
                        System.out.println(" - " + md.getDepartament());
                    }

                    ManagerDepartament newDepartament = null;
                    while(true) {
                        System.out.print("Digite seu novo departamento: ");
                        String input = DataStorage.scanner.nextLine();

                        for (ManagerDepartament md : ManagerDepartament.values()) {
                            if (md.getDepartament().equalsIgnoreCase(input)) {
                                newDepartament = md;
                                break;
                            }
                        }

                        if (newDepartament != null) {
                            targetManager.setManagerDepartament(newDepartament);
                            System.out.println("✅ Departamento editado com sucesso!");
                            break;
                        } else {
                            System.out.println("⚠ Departamento inválido. Tente novamente.");
                        }
                    }

                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 2 -> {
                    System.out.println("Responsabilidades disponíveis:");
                    for (ManagerResponsibility mr : ManagerResponsibility.values()) {
                        System.out.println(" - " + mr.getResponsability());
                    }

                    ManagerResponsibility newResponsibility = null;
                    while(true) {
                        System.out.print("Digite sua nova responsabilidade: ");
                        String input = DataStorage.scanner.nextLine();

                        for (ManagerResponsibility mr : ManagerResponsibility.values()) {
                            if (mr.getResponsability().equalsIgnoreCase(input)) {
                                newResponsibility = mr;
                                break;
                            }
                        }

                        if (newResponsibility != null) {
                            targetManager.setManagerResponsibility(newResponsibility);
                            System.out.println("✅ Responsabilidade editada com sucesso!");
                            break;
                        } else {
                            System.out.println("⚠ Responsabilidade inválida. Tente novamente.");
                        }
                    }

                    System.out.print("Continuar editando? (SIM/NAO): ");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) return;
                }
                case 3 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida. Tente novamente.");
            }
        }
    }

    @Override
    public void deleteManager(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < DataStorage.managersRegisters.length; i++) {
            if (DataStorage.managersRegisters[i].getId() == id) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("⚠ Gerente com ID " + id + " não encontrado.");
            return;
        }

        Manager[] deleteManager = new Manager[DataStorage.managersRegisters.length - 1];
        for (int i = 0, j = 0; i < DataStorage.managersRegisters.length; i++) {
            if (i != indexToRemove) {
                deleteManager[j++] = DataStorage.managersRegisters[i];
            }
        }

        DataStorage.managersRegisters = deleteManager;
        System.out.println("✅ Gerente com ID " + id + " removido com sucesso!");
    }

    @Override
    public void managerTeam(int id) {
        Manager targetManager = null;
        for (Manager manager : DataStorage.managersRegisters) {
            if (manager.getId() == id) {
                targetManager = manager;
                break;
            }
        }

        if (targetManager == null) {
            System.out.println("⚠ Gerente com ID " + id + " não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n------ GERENCIAR EQUIPE DO GERENTE: " + targetManager.getName() + " ------");
            System.out.println("1 - Ver equipe atual");
            System.out.println("2 - Adicionar developer à equipe");
            System.out.println("3 - Remover developer da equipe");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("------ EQUIPE ATUAL ------");
                    Employee[] currentTeam = targetManager.getEmployees();
                    if (currentTeam != null && currentTeam.length > 0) {
                        for (Employee member : currentTeam) {
                            System.out.printf("ID   : %d%n", member.getId());
                            System.out.printf("Nome : %s%n", member.getName());
                            System.out.println("--------------------------");
                        }
                    } else {
                        System.out.println("⚠ Nenhum membro na equipe.");
                    }
                }
                case 2 -> {
                    System.out.println("------ ADICIONAR DESENVOLVEDOR ------");
                    if (DataStorage.developersRegisters.length == 0) {
                        System.out.println("⚠ Nenhum desenvolvedor cadastrado para adicionar.");
                        break;
                    }

                    System.out.println("Desenvolvedores disponíveis:");
                    for (Developer dev : DataStorage.developersRegisters) {
                        System.out.printf("ID = %d, Nome = %s%n", dev.getId(), dev.getName());
                    }

                    System.out.print("Digite o ID do desenvolvedor: ");
                    int devId = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();

                    Developer devToAdd = null;
                    for (Developer dev : DataStorage.developersRegisters) {
                        if (dev.getId() == devId) {
                            devToAdd = dev;
                            break;
                        }
                    }

                    if (devToAdd == null) {
                        System.out.println("⚠ Desenvolvedor não encontrado.");
                        break;
                    }

                    Employee[] oldTeam = targetManager.getEmployees() == null ? new Employee[0] : targetManager.getEmployees();
                    boolean alreadyInTeam = false;
                    for(Employee member : oldTeam) {
                        if(member.getId() == devToAdd.getId()){
                            alreadyInTeam = true;
                            break;
                        }
                    }

                    if(alreadyInTeam) {
                        System.out.println("⚠ Este desenvolvedor já faz parte da equipe.");
                    } else {
                        Employee[] newTeam = new Employee[oldTeam.length + 1];
                        for (int i = 0; i < oldTeam.length; i++) newTeam[i] = oldTeam[i];
                        newTeam[oldTeam.length] = devToAdd;
                        targetManager.setEmployees(newTeam);
                        System.out.println("✅ Desenvolvedor adicionado com sucesso!");
                    }
                }
                case 3 -> {
                    System.out.println("------ REMOVER DESENVOLVEDOR ------");
                    Employee[] team = targetManager.getEmployees();
                    if (team == null || team.length == 0) {
                        System.out.println("⚠ A equipe está vazia. Nenhum membro para remover.");
                        break;
                    }

                    System.out.println("Membros da equipe atual:");
                    for (Employee member : team) {
                        System.out.printf("ID = %d, Nome = %s%n", member.getId(), member.getName());
                    }
                    System.out.print("Digite o ID do membro que deseja remover: ");
                    int devIdToRemove = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();

                    int index = -1;
                    for (int i = 0; i < team.length; i++) {
                        if (team[i].getId() == devIdToRemove) {
                            index = i;
                            break;
                        }
                    }

                    if (index == -1) {
                        System.out.println("⚠ Membro não encontrado na equipe.");
                    } else {
                        Employee[] newTeam = new Employee[team.length - 1];
                        for (int i = 0, j = 0; i < team.length; i++) {
                            if (i != index) newTeam[j++] = team[i];
                        }
                        targetManager.setEmployees(newTeam);
                        System.out.println("✅ Membro removido com sucesso!");
                    }
                }
                case 4 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida. Tente novamente.");
            }
        }
    }

    @Override
    public void technicalInformations(Manager manager) {
        System.out.println("\n=========================================");
        System.out.println("💼 INFORMAÇÕES TÉCNICAS DO GERENTE 💼");
        System.out.println("=========================================");
        System.out.println("Departamento: " + manager.getManagerDepartament().getDepartament());
        System.out.println("Responsabilidade: " + manager.getManagerResponsibility().getResponsability());

        if (manager.getEmployees() != null && manager.getEmployees().length > 0) {
            System.out.println("\nEquipe:");
            for (Employee employee : manager.getEmployees()) {
                System.out.printf("ID   : %d%n", employee.getId());
                System.out.printf("Nome : %s%n", employee.getName());
                System.out.println("--------------------------");
            }
        }
        System.out.println();
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
}
