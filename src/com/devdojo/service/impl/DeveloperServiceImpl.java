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
        System.out.println("\n=========================================");
        System.out.println("📝 INFORMACOES PESSOAIS DO DESENVOLVEDOR 📝");
        System.out.println("=========================================");

        System.out.print("ID do desenvolvedor: ");
        int developerId = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        System.out.print("Nome do desenvolvedor: ");
        String developerName = DataStorage.scanner.nextLine();

        System.out.print("CPF do desenvolvedor: ");
        String developerCpf = DataStorage.scanner.nextLine();

        System.out.print("Idade do desenvolvedor: ");
        int developerAge = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        Person.Sex developerSex = null;
        while (developerSex == null) {
            System.out.print("Sexo (MASCULINO/FEMININO): ");
            String inputSex = DataStorage.scanner.nextLine().trim();
            if (inputSex.equalsIgnoreCase("masculino")) {
                developerSex = Person.Sex.MASCULINO;
            } else if (inputSex.equalsIgnoreCase("feminino")) {
                developerSex = Person.Sex.FEMININO;
            } else {
                System.out.println("⚠ Sexo inválido. Digite MASCULINO ou FEMININO.");
            }
        }

        System.out.print("Email do desenvolvedor: ");
        String developerEmail = DataStorage.scanner.nextLine();

        System.out.print("Telefone do desenvolvedor: ");
        String developerPhone = DataStorage.scanner.nextLine();

        System.out.println("\n=========================================");
        System.out.println("💻 INFORMACOES TECNICAS DO DESENVOLVEDOR 💻");
        System.out.println("=========================================");

        System.out.print("Salario do desenvolvedor: R$ ");
        double developerSalary = DataStorage.scanner.nextDouble();
        DataStorage.scanner.nextLine();

        System.out.println("\nEspecialidades disponíveis:");
        for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
            System.out.println(" - " + ds.getSpecialty());
        }

        DeveloperSpecialty developerSpecialty = null;
        while (developerSpecialty == null) {
            System.out.print("Digite sua especialidade: ");
            String input = DataStorage.scanner.nextLine();
            for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                if (ds.getSpecialty().equalsIgnoreCase(input)) {
                    developerSpecialty = ds;
                    break;
                }
            }
            if (developerSpecialty == null) {
                System.out.println("⚠ Especialidade inválida. Tente novamente.");
            }
        }

        System.out.println("\nLinguagens disponíveis:");
        for (DeveloperLanguages dl : DeveloperLanguages.values()) {
            System.out.println(" - " + dl);
        }

        System.out.print("Quantas linguagens quer adicionar? ");
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
                if (!valid) System.out.println("⚠ Linguagem inválida. Tente novamente.");
            }
        }

        System.out.println("\nExperiências disponíveis:");
        for (DeveloperExperience de : DeveloperExperience.values()) {
            System.out.println(" - " + de.getExperience());
        }

        DeveloperExperience developerExperience = null;
        while (developerExperience == null) {
            System.out.print("Digite sua experiência: ");
            String input = DataStorage.scanner.nextLine();
            for (DeveloperExperience de : DeveloperExperience.values()) {
                if (de.getExperience().equalsIgnoreCase(input)) {
                    developerExperience = de;
                    break;
                }
            }
            if (developerExperience == null) System.out.println("⚠ Experiência inválida. Tente novamente.");
        }

        System.out.println("\nBenefícios disponíveis:");
        for (EmployeeBenefits eb : EmployeeBenefits.values()) {
            System.out.println(" - " + eb.getBenefits()[0]);
        }

        System.out.print("Quantos benefícios quer adicionar? ");
        int benefitsQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();
        EmployeeBenefits[] developerBenefits = new EmployeeBenefits[benefitsQuantity];

        for (int i = 0; i < benefitsQuantity; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print((i + 1) + "º benefício: ");
                String input = DataStorage.scanner.nextLine().trim();
                for (EmployeeBenefits db : EmployeeBenefits.values()) {
                    if (db.getBenefits()[0].equalsIgnoreCase(input)) {
                        developerBenefits[i] = db;
                        valid = true;
                        break;
                    }
                }
                if (!valid) System.out.println("⚠ Benefício inválido. Tente novamente.");
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

        System.out.println("\n✅ Desenvolvedor cadastrado com sucesso!");
    }

    @Override
    public void showDevelopers() {
        System.out.println("\n=========================================");
        System.out.println("👥 DESENVOLVEDORES CADASTRADOS 👥");
        System.out.println("=========================================");
        for (Developer dev : DataStorage.developersRegisters) {
            System.out.printf("ID   : %d%n", dev.getId());
            System.out.printf("Nome : %s%n", dev.getName());
            System.out.println("-----------------------------------------");
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
            System.out.println("⚠ Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n=========================================");
        System.out.println("📄 RELATÓRIO DO DESENVOLVEDOR: " + targetDeveloper.getName());
        System.out.println("=========================================");
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
            System.out.println("⚠ Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("✏️ EDITAR INFORMAÇÕES PESSOAIS: " + targetDeveloper.getName());
            System.out.println("=========================================");
            System.out.println("1 - Nome");
            System.out.println("2 - CPF");
            System.out.println("3 - Idade");
            System.out.println("4 - Sexo");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção: ");

            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Digite o novo nome: ");
                    targetDeveloper.setName(DataStorage.scanner.nextLine());
                    System.out.println("✅ Nome editado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Digite o novo CPF: ");
                    targetDeveloper.setCpf(DataStorage.scanner.nextLine());
                    System.out.println("✅ CPF editado com sucesso!");
                }
                case 3 -> {
                    System.out.print("Digite a nova idade: ");
                    targetDeveloper.setAge(DataStorage.scanner.nextInt());
                    DataStorage.scanner.nextLine();
                    System.out.println("✅ Idade editada com sucesso!");
                }
                case 4 -> {
                    Person.Sex newSex = null;
                    while (newSex == null) {
                        System.out.print("Digite o novo sexo (MASCULINO/FEMININO): ");
                        String input = DataStorage.scanner.nextLine().trim();
                        if (input.equalsIgnoreCase("masculino")) newSex = Person.Sex.MASCULINO;
                        else if (input.equalsIgnoreCase("feminino")) newSex = Person.Sex.FEMININO;
                        else System.out.println("⚠ Sexo inválido. Digite MASCULINO ou FEMININO.");
                    }
                    targetDeveloper.setSex(newSex);
                    System.out.println("✅ Sexo editado com sucesso!");
                }
                case 5 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida! Tente novamente.");
            }

            System.out.print("Deseja continuar editando? (SIM/NAO): ");
            String cont = DataStorage.scanner.nextLine().trim();
            if (cont.equalsIgnoreCase("nao")) {
                System.out.println("Voltando ao menu anterior...");
                return;
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
            System.out.println("⚠ Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("💻 EDITAR INFORMAÇÕES TECNICAS: " + targetDeveloper.getName());
            System.out.println("=========================================");
            System.out.println("1 - Especialidade");
            System.out.println("2 - Linguagens");
            System.out.println("3 - Experiência");
            System.out.println("4 - Voltar");
            System.out.print("Escolha a opção: ");

            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("\nEspecialidades disponíveis:");
                    for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                        System.out.println(" - " + ds.getSpecialty());
                    }
                    DeveloperSpecialty newSpec = null;
                    while (newSpec == null) {
                        System.out.print("Digite a nova especialidade: ");
                        String input = DataStorage.scanner.nextLine();
                        for (DeveloperSpecialty ds : DeveloperSpecialty.values()) {
                            if (ds.getSpecialty().equalsIgnoreCase(input)) {
                                newSpec = ds;
                                break;
                            }
                        }
                        if (newSpec == null) System.out.println("⚠ Especialidade inválida. Tente novamente.");
                    }
                    targetDeveloper.setDeveloperSpecialty(newSpec);
                    System.out.println("✅ Especialidade editada com sucesso!");
                }
                case 2 -> {
                    System.out.println("\nLinguagens disponíveis:");
                    for (DeveloperLanguages dl : DeveloperLanguages.values()) System.out.println(" - " + dl);
                    System.out.print("Quantas linguagens quer adicionar? ");
                    int qty = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();
                    DeveloperLanguages[] newLangs = new DeveloperLanguages[qty];
                    for (int i = 0; i < qty; i++) {
                        boolean valid = false;
                        while (!valid) {
                            System.out.print((i + 1) + "ª linguagem: ");
                            String input = DataStorage.scanner.nextLine().toUpperCase().replace(" ", "_");
                            for (DeveloperLanguages dl : DeveloperLanguages.values()) {
                                if (dl.name().equals(input)) {
                                    newLangs[i] = dl;
                                    valid = true;
                                    break;
                                }
                            }
                            if (!valid) System.out.println("⚠ Linguagem inválida. Tente novamente.");
                        }
                    }
                    targetDeveloper.setDeveloperLanguages(newLangs);
                    System.out.println("✅ Linguagens editadas com sucesso!");
                }
                case 3 -> {
                    System.out.println("\nExperiências disponíveis:");
                    for (DeveloperExperience de : DeveloperExperience.values()) System.out.println(" - " + de.getExperience());
                    DeveloperExperience newExp = null;
                    while (newExp == null) {
                        System.out.print("Digite a nova experiência: ");
                        String input = DataStorage.scanner.nextLine();
                        for (DeveloperExperience de : DeveloperExperience.values()) {
                            if (de.getExperience().equalsIgnoreCase(input)) {
                                newExp = de;
                                break;
                            }
                        }
                        if (newExp == null) System.out.println("⚠ Experiência inválida. Tente novamente.");
                    }
                    targetDeveloper.setDeveloperExperience(newExp);
                    System.out.println("✅ Experiência editada com sucesso!");
                }
                case 4 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida! Tente novamente.");
            }

            System.out.print("Deseja continuar editando? (SIM/NAO): ");
            String cont = DataStorage.scanner.nextLine().trim();
            if (cont.equalsIgnoreCase("nao")) return;
        }
    }

    @Override
    public void deleteDeveloper(int id) {
        Developer targetDeveloper = null;
        int index = -1;
        for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
            if (DataStorage.developersRegisters[i].getId() == id) {
                targetDeveloper = DataStorage.developersRegisters[i];
                index = i;
                break;
            }
        }
        if (targetDeveloper == null) {
            System.out.println("⚠ Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        Developer[] newArray = new Developer[DataStorage.developersRegisters.length - 1];
        int j = 0;
        for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
            if (i != index) newArray[j++] = DataStorage.developersRegisters[i];
        }
        DataStorage.developersRegisters = newArray;
        System.out.println("✅ Desenvolvedor " + targetDeveloper.getName() + " deletado com sucesso!");
    }

    @Override
    public void technicalInformations(Developer developer) {
        System.out.println("\n💻 INFORMAÇÕES TÉCNICAS 💻");
        System.out.println("Nível de experiência : " + developer.getDeveloperExperience());
        System.out.println("Especialidade       : " + developer.getSDeveloperSpecialty());
        System.out.println("Linguagens utilizadas:");
        for (DeveloperLanguages dl : developer.getDeveloperLanguages()) {
            System.out.println(" - " + dl.getLanguages());
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
            System.out.println("⚠ Desenvolvedor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\n=========================================");
        System.out.println("📁 PROJETO DO DESENVOLVEDOR: " + targetDeveloper.getName());
        System.out.println("=========================================");

        if (targetDeveloper.getProject() == null) {
            System.out.println("Nenhum projeto associado.");
            return;
        }

        System.out.println("Título          : " + targetDeveloper.getProject().getTitle());
        System.out.println("Descrição       : " + targetDeveloper.getProject().getDescription());
        System.out.println("Data de início  : " + targetDeveloper.getProject().getStartDate());
        System.out.println("Data de conclusão: " + targetDeveloper.getProject().getEndDate());

        if (targetDeveloper.getProject().getDevelopers() != null) {
            System.out.println("Equipe:");
            for (Developer team : targetDeveloper.getProject().getDevelopers()) {
                System.out.println(" - " + team.getName());
            }
        } else {
            System.out.println("Sem equipe! Somente você no momento.");
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
