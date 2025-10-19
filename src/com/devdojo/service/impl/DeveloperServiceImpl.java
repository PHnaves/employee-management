package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Person;
import com.devdojo.domain.enums.Developer.DeveloperExperience;
import com.devdojo.domain.enums.Developer.DeveloperLanguages;
import com.devdojo.domain.enums.Developer.DeveloperSpecialty;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.DeveloperService;
import com.devdojo.storage.DataStorage;

public class DeveloperServiceImpl implements DeveloperService {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public void createDeveloper() {
        System.out.println("-----Informacoes pessoais-----");
        System.out.println("ID do desenvolvedor: ");
        int developerId = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        System.out.println("Nome do desenvolvedor");
        String developerName = DataStorage.scanner.nextLine();

        System.out.println("Cpf do desenvolvedor: ");
        String developerCpf = DataStorage.scanner.nextLine();

        System.out.println("Idade do desenvolvedor: ");
        int developerAge = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        Person.Sex developerSex = null;
        while(developerSex == null) {
            System.out.println("Sexo - MASCULINO OU FEMININO:");
            String inputSex = DataStorage.scanner.nextLine().trim(); // remove espaços extras
            if (inputSex.equalsIgnoreCase("masculino")) {
                developerSex = Person.Sex.MASCULINO;
            } else if (inputSex.equalsIgnoreCase("feminino")) {
                developerSex = Person.Sex.FEMININO;
            } else {
                System.out.println("Sexo invalido. Por favor, digite um dos valores da lista");
            }
        }

        System.out.println("Email do desenvolvedor: ");
        String developerEmail = DataStorage.scanner.nextLine();

        System.out.println("Telefone do desenvolvedor: ");
        String developerPhone = DataStorage.scanner.nextLine();

        System.out.println("-----Informacoes tecnicas-----");
        System.out.println("Salario do desenvolvedor: ");
        double developerSalary = DataStorage.scanner.nextDouble();
        DataStorage.scanner.nextLine();

        System.out.println("Especialidades disponiveis");
        for (DeveloperSpecialty developerSpecialty : DeveloperSpecialty.values()) {
            System.out.println(developerSpecialty.getSpecialty());
        }

        DeveloperSpecialty developerSpecialty = null;
        while(true) {
            System.out.println("Digite respectivamente sua especialidade: ");
            String input = DataStorage.scanner.nextLine();

            for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                if (ds.getSpecialty().equalsIgnoreCase(input)) {
                    developerSpecialty = ds;
                    break;
                }
            }

            if (developerSpecialty != null) {
                break;
            } else {
                System.out.println("Especialidade invalida. Por favor, digite um dos valores da lista");
            }
        }

        System.out.println("Linguagens");
        for (DeveloperLanguages developerLanguages : DeveloperLanguages.values()) {
            System.out.println(developerLanguages);
        }

        System.out.println("Quantas linguagens quer adicionar ao cadastro? ");
        int languagesQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();
        DeveloperLanguages[] developerLanguages = new DeveloperLanguages[languagesQuantity];

        for (int i = 0; i < languagesQuantity; i++) {
            boolean valid = false;

            while (!valid) {
                System.out.print((i + 1) + "ª linguagem: ");
                String input = DataStorage.scanner.nextLine().toUpperCase().replace(" ", "_");

                for (DeveloperLanguages dl : DeveloperLanguages.values()) {
                    if (dl.name().equals(input)) {
                        developerLanguages[i] = dl;
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println("Linguagem inválida. Por favor, digite um dos valores da lista");
                }
            }
        }

        System.out.println("Experiencias disponiveis");
        for(DeveloperExperience developerExperience : DeveloperExperience.values()) {
            System.out.println(developerExperience.getExperience());
        }

        DeveloperExperience developerExperience = null;
        while(true) {
            System.out.println("Digite respectivamente sua experiencia: ");
            String input = DataStorage.scanner.nextLine();

            for (DeveloperExperience de : DeveloperExperience.values()) {
                if (de.getExperience().equalsIgnoreCase(input)) {
                    developerExperience = de;
                    break;
                }
            }

            if(developerExperience != null) {
                break;
            } else {
                System.out.println("Experiencia invalida. Por favor, digite um dos valores da lista.");
            }

        }

        System.out.println("Beneficios disponivies");
        for (EmployeeBenefits employeeBenefits : EmployeeBenefits.values()) {
            System.out.println(employeeBenefits.getBenefits()[0]);
        }

        System.out.println("Quantos beneficios quer adicionar? ");
        int benefitsQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();
        EmployeeBenefits[] developerBenefits = new EmployeeBenefits[benefitsQuantity];

        for (int i = 0; i < benefitsQuantity; i++) {
            boolean valid = false;

            while (!valid) {
                System.out.print((i + 1) + "ª beneficio: ");
                String input = DataStorage.scanner.nextLine().trim();

                for (EmployeeBenefits db : EmployeeBenefits.values()) {
                    if (db.getBenefits()[0].equalsIgnoreCase(input)) {
                        developerBenefits[i] = db;
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println("Beneficio invalido. Por favor, digite um dos valores da lista");
                }
            }
        }

        Developer newDev = new Developer(
          developerId,
          developerName,
          developerCpf,
          developerAge,
          developerSex,
          developerEmail,
          developerPhone,
          developerSalary,
          developerSpecialty,
          developerLanguages,
          developerExperience,
          developerBenefits
        );

        Developer[] newDeveloperRegisters = new Developer[DataStorage.developersRegisters.length + 1];

        for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
            newDeveloperRegisters[i] = DataStorage.developersRegisters[i];
        }

        newDeveloperRegisters[DataStorage.developersRegisters.length] = newDev;

        DataStorage.developersRegisters = newDeveloperRegisters;

    }

    @Override
    public void showDevelopers() {
        System.out.println("Desenvolvedores cadastrados");
        for (Developer dev : DataStorage.developersRegisters) {
            System.out.println("ID = " + dev.getId());
            System.out.println("Nome = " + dev.getName());
            System.out.println("-------------------------------");
        }
    }

    @Override
    public void readDeveloper(int id) {
        Developer targetDeveloper = null;
        for (Developer dev : DataStorage.developersRegisters) {
            if (dev.getId() == id) {
                targetDeveloper = dev;
                break;
            }
        }

        if (targetDeveloper == null) {
            System.out.println("Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n------ RELATORIO DESENVOLVEDOR: " + targetDeveloper.getName() + " ------");
        this.generateReport(targetDeveloper);
        System.out.println();
        this.technicalInformations(targetDeveloper);
        System.out.println();
        this.benefits(targetDeveloper);
    }

    @Override
    public void updateDeveloper(int id) {
        Developer targetDeveloper = null;
        for (Developer dev : DataStorage.developersRegisters) {
            if (dev.getId() == id) {
                targetDeveloper = dev;
                break;
            }
        }

        if (targetDeveloper == null) {
            System.out.println("Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMACOES PESSOAIS DESENVOLVEDOR: " + targetDeveloper.getName() + " ------");
            System.out.println("1 - Nome");
            System.out.println("2 - CPF");
            System.out.println("3 - Idade");
            System.out.println("4 - Sexo");
            System.out.println("5 - voltar");
            System.out.println("Digite a opcao correspondente: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Digite o novo nome: ");
                    String newName = DataStorage.scanner.nextLine();
                    targetDeveloper.setName(newName);
                    System.out.println("Nome editado com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Digite o novo CPF: ");
                    String newCpf = DataStorage.scanner.nextLine();
                    targetDeveloper.setCpf(newCpf);
                    System.out.println("CPF editado com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Digite a nova idade: ");
                    int newAge = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();
                    targetDeveloper.setAge(newAge);
                    System.out.println("Idade editada com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 4:
                    boolean isCorrect = false;
                    while(!isCorrect) {
                        System.out.println("Digite o novo Sexo - MASCULINO OU FEMININO:");
                        String newSex = DataStorage.scanner.nextLine().trim(); // remove espaços extras
                        if (newSex.equalsIgnoreCase("masculino")) {
                            targetDeveloper.setSex(Person.Sex.MASCULINO);
                            System.out.println("Sexo editado com sucesso!");
                            isCorrect = true;
                        } else if (newSex.equalsIgnoreCase("feminino")) {
                            targetDeveloper.setSex(Person.Sex.FEMININO);
                            System.out.println("Sexo editado com sucesso!");
                            isCorrect = true;
                        } else {
                            System.out.println("Sexo inválido, digite novamente.");
                        }
                    }

                    System.out.println("Quer continuar editando? Sim ou Nao");
                    continueEdit = DataStorage.scanner.nextLine();
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
        Developer targetDeveloper = null;
        for (Developer dev : DataStorage.developersRegisters) {
            if (dev.getId() == id) {
                targetDeveloper = dev;
                break;
            }
        }

        if (targetDeveloper == null) {
            System.out.println("Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR INFORMACOES TECNICAS DESENVOLVEDOR: " + targetDeveloper.getName() + " ------");
            System.out.println("1 - Especialidade");
            System.out.println("2 - Linguagens");
            System.out.println("3 - Experiencia");
            System.out.println("4 - Sair");
            System.out.println("Digite a opcao correspondente: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Especialidades disponiveis");
                    for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                        System.out.println(ds.getSpecialty());
                    }

                    DeveloperSpecialty newSpecialty = null;
                    while(true) {
                        System.out.println("Digite respectivamente sua nova especialidade: ");
                        String input = DataStorage.scanner.nextLine();

                        for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                            if (ds.getSpecialty().equalsIgnoreCase(input)) {
                                newSpecialty = ds;
                                break;
                            }
                        }

                        if (newSpecialty != null) {
                            targetDeveloper.setDeveloperSpecialty(newSpecialty);
                            System.out.println("Especialidade editada com sucesso!");
                            break;
                        } else {
                            System.out.println("Especialidade invalida. Por favor, digite um dos valores da lista");
                        }
                    }

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Linguagens disponiveis");
                    for (DeveloperLanguages dl : DeveloperLanguages.values()) {
                        System.out.println(dl);
                    }

                    System.out.println("Quantas linguagens quer adicionar? ");
                    int languagesQuantity = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();
                    DeveloperLanguages[] newLanguagesSelect = new DeveloperLanguages[languagesQuantity];

                    for (int i = 0; i < languagesQuantity; i++) {
                        boolean valid = false;

                        while (!valid) {
                            System.out.print((i + 1) + "ª linguagem: ");
                            String input = DataStorage.scanner.nextLine().toUpperCase().replace(" ", "_");

                            for (DeveloperLanguages dl : DeveloperLanguages.values()) {
                                if (dl.name().equals(input)) {
                                    newLanguagesSelect[i] = dl;
                                    valid = true;
                                    break;
                                }
                            }

                            if (!valid) {
                                System.out.println("Linguagem inválida, digite novamente!");
                            }
                        }
                    }
                    targetDeveloper.setDeveloperLanguages(newLanguagesSelect);
                    System.out.println("Linguagem editada com sucesso!");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Experiencias disponiveis");
                    for(DeveloperExperience de : DeveloperExperience.values()) {
                        System.out.println(de.getExperience());
                    }

                    DeveloperExperience newExperience = null;
                    while(true) {
                        System.out.println("Digite respectivamente sua nova experiencia: ");
                        String input = DataStorage.scanner.nextLine();

                        for (DeveloperExperience de : DeveloperExperience.values()) {
                            if (de.getExperience().equalsIgnoreCase(input)) {
                                newExperience = de;
                                break;
                            }
                        }

                        if(newExperience != null) {
                            targetDeveloper.setDeveloperExperience(newExperience);
                            break;
                        } else {
                            System.out.println("Experiencia invalida. Por favor, digite um dos valores da lista.");
                        }

                    }

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Digite somente umas das opcoes!");
                    break;
            }
        }
    }

    @Override
    public void deleteDeveloper(int id) {
        int indexToRemove = -1;

        for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
            if (DataStorage.developersRegisters[i].getId() == id) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
            return;
        }

        Developer[] deleteDeveloper = new Developer[DataStorage.developersRegisters.length - 1];

        for (int i = 0, j = 0; i < DataStorage.developersRegisters.length; i++) {
            if (i != indexToRemove) {
                deleteDeveloper[j++] = DataStorage.developersRegisters[i];
            }
        }

        DataStorage.developersRegisters = deleteDeveloper;

        System.out.println("Desenvolvedor com ID " + id + " removido com sucesso!");
    }

    @Override
    public void technicalInformations(Developer developer) {
        System.out.println("Informacoes tecnicas");
        System.out.println("Nivel de experiencia: " + developer.getDeveloperExperience());
        System.out.println("Especialidade: " + developer.getSDeveloperSpecialty());
        System.out.println("Linguagens utilizadas");
        for (DeveloperLanguages dl : developer.getDeveloperLanguages()) {
            System.out.println(dl.getLanguages());
        }
    }

    @Override
    public void project(int id) {
        Developer targetDeveloper = null;
        for (Developer dev : DataStorage.developersRegisters) {
            if (dev.getId() == id) {
                targetDeveloper = dev;
                break;
            }
        }

        if (targetDeveloper == null) {
            System.out.println("Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n------ PROJETO DESENVOLVEDOR: " + targetDeveloper.getName() + " ------");
        if (targetDeveloper.getProject() == null){
            System.out.println("Nenhum projeto associado");
            return;
        }
        System.out.println("Titulo: " + targetDeveloper.getProject().getTitle());
        System.out.println("Descricao");
        System.out.println(targetDeveloper.getProject().getDescription());
        System.out.println("Data de inicio: " + targetDeveloper.getProject().getStartDate());
        System.out.println("Data de conclusao: " + targetDeveloper.getProject().getEndDate());
        if (targetDeveloper.getProject().getDevelopers() != null ) {
            System.out.println("Equipe");
            for (Developer team: targetDeveloper.getProject().getDevelopers()) {
                System.out.println(team.getName());
            }
        }else {
            System.out.println("Sem equipe! somente voce no momento");
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
}
