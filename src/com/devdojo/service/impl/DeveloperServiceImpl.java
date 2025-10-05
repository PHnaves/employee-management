package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Person;
import com.devdojo.domain.enums.Developer.DeveloperExperience;
import com.devdojo.domain.enums.Developer.DeveloperLanguages;
import com.devdojo.domain.enums.Developer.DeveloperSpecialty;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.DeveloperService;

import java.util.Scanner;

public class DeveloperServiceImpl implements DeveloperService {
    static final Scanner scanner = new Scanner(System.in);
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private Employee[] developersRegistereds = new Employee[]{};

    @Override
    public void createDeveloper() {
        System.out.println("-----Informacoes pessoais-----");
        System.out.println("ID do usuario: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome do funcionario");
        String employeeName = scanner.nextLine();

        System.out.println("Cpf do funcionario: ");
        String employeeCpf = scanner.nextLine();

        System.out.println("Idade do funcionario: ");
        int employeeAge = scanner.nextInt();
        scanner.nextLine();

        Person.Sex employeeSex = null;
        while(employeeSex == null) {
            System.out.println("Sexo - MASCULINO OU FEMININO:");
            String inputSex = scanner.nextLine().trim(); // remove espaços extras
            if (inputSex.equalsIgnoreCase("masculino")) {
                employeeSex = Person.Sex.MASCULINO;
            } else if (inputSex.equalsIgnoreCase("feminino")) {
                employeeSex = Person.Sex.FEMININO;
            } else {
                System.out.println("Sexo inválido, digite novamente.");
            }
        }


        System.out.println("Email do funcionario: ");
        String employeeEmail = scanner.nextLine();

        System.out.println("Telefone do funcionario: ");
        String employeePhone = scanner.nextLine();

        System.out.println("-----Informacoes tecnicas-----");
        System.out.println("Salario do funcionario: ");
        double employeeSalary = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Especialidades disponiveis");
        for (DeveloperSpecialty developerSpecialty : DeveloperSpecialty.values()) {
            System.out.println(developerSpecialty);
        }

        System.out.println("Digite respectivamente sua especialidade: ");
        DeveloperSpecialty employeeSpecialty = DeveloperSpecialty.valueOf(scanner.nextLine().toUpperCase().trim());

        System.out.println("Linguagens");
        for (DeveloperLanguages developerLanguages : DeveloperLanguages.values()) {
            System.out.println(developerLanguages);
        }

        System.out.println("Quantas linguagens quer adicionar ao cadastro? ");
        int languagesQuantity = scanner.nextInt();
        scanner.nextLine();
        DeveloperLanguages[] languagesSelect = new DeveloperLanguages[languagesQuantity];

        for (int i = 0; i < languagesQuantity; i++) {
            boolean valid = false;

            while (!valid) {
                System.out.print((i + 1) + "ª linguagem: ");
                String input = scanner.nextLine().toUpperCase().replace(" ", "_");

                for (DeveloperLanguages dl : DeveloperLanguages.values()) {
                    if (dl.name().equals(input)) {
                        languagesSelect[i] = dl;
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println("Linguagem inválida, digite novamente!");
                }
            }
        }

        System.out.println("Experiencias disponiveis");
        for(DeveloperExperience developerExperience : DeveloperExperience.values()) {
            System.out.println(developerExperience);
        }
        System.out.println("Informe a experiencia desejada");
        DeveloperExperience employeeExperience = DeveloperExperience.valueOf(scanner.nextLine().toUpperCase().trim());

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

        Employee dev = new Developer(
          employeeId,
          employeeName,
          employeeCpf,
          employeeAge,
          employeeSex,
          employeeEmail,
          employeePhone,
          employeeSalary,
          employeeSpecialty,
          languagesSelect,
          employeeExperience,
          benefitsSelect
        );

        Employee[] temp = new Employee[developersRegistereds.length + 1];

        for (int i = 0; i < this.developersRegistereds.length; i++) {
            temp[i] = developersRegistereds[i];
        }

        temp[this.developersRegistereds.length] = dev;

        this.developersRegistereds = temp;

        employeeService.generateReport(dev);
        this.technicalInformations((Developer) dev);

    }

    @Override
    public void showDevelopers() {
        System.out.println("Desenvolvedores cadastrados");
        for (Employee employees : developersRegistereds) {
            System.out.println("ID = " + employees.getId());
            System.out.println("Nome = " + employees.getName());
            System.out.println("-------------------------------");
        }
    }

    @Override
    public void readDeveloper(int id) {
        for (Employee employee : this.developersRegistereds) {
            if (employee.getId() == id) {
                System.out.println("---------- RELATORIO COMPLETO ----------");
                this.generateReport((Employee) employee);
                System.out.println();
                this.technicalInformations((Developer) employee);
                System.out.println();
                this.benefits((Employee) employee);
                break;
            }
        }
    }

    @Override
    public void updateDeveloper(int id) {
        for (Employee employee : developersRegistereds) {
            if(employee.getId() == id) {
                boolean isEdit = true;
                while(isEdit) {
                    System.out.println("O que desejas editar?");
                    System.out.println("1 - Nome");
                    System.out.println("2 - CPF");
                    System.out.println("3 - Idade");
                    System.out.println("4 - Sexo");
                    System.out.println("5 - voltar");
                    System.out.println("Digite a opcao correspondente: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            System.out.println("Digite o novo nome: ");
                            String newName = scanner.nextLine();
                            employee.setName(newName);
                            System.out.println("Quer continuar editando? Sim ou Nao");
                            String continueEdit = scanner.nextLine();
                            if (continueEdit.equalsIgnoreCase("nao")) {
                                isEdit = false;
                            }
                            break;
                        case 2:
                            System.out.println("Digite o novo CPF: ");
                            String newCpf = scanner.nextLine();
                            employee.setCpf(newCpf);
                            System.out.println("Quer continuar editando? Sim ou Nao");
                            continueEdit = scanner.nextLine();
                            if (continueEdit.equalsIgnoreCase("nao")) {
                                isEdit = false;
                            }
                            break;
                        case 3:
                            System.out.println("Digite a nova idade: ");
                            int newAge = scanner.nextInt();
                            employee.setAge(newAge);
                            System.out.println("Quer continuar editando? Sim ou Nao");
                            continueEdit = scanner.nextLine();
                            if (continueEdit.equalsIgnoreCase("nao")) {
                                isEdit = false;
                            }
                            break;
                        case 4:
                            boolean isCorrect = false;
                            while(isCorrect != true) {
                                System.out.println("Digite o novo Sexo - MASCULINO OU FEMININO:");
                                String newSex = scanner.nextLine().trim(); // remove espaços extras
                                if (newSex.equalsIgnoreCase("masculino")) {
                                    employee.setSex(Person.Sex.MASCULINO);
                                    isCorrect = true;
                                    break;
                                } else if (newSex.equalsIgnoreCase("feminino")) {
                                    employee.setSex(Person.Sex.FEMININO);
                                    isCorrect = true;
                                    break;
                                } else {
                                    System.out.println("Sexo inválido, digite novamente.");
                                }
                                System.out.println("Quer continuar editando? Sim ou Nao");
                                continueEdit = scanner.nextLine();
                                if (continueEdit.equalsIgnoreCase("nao")) {
                                    isEdit = false;
                                }
                            }
                            break;
                        case 5:
                            isEdit = false;
                            break;

                        default:
                            System.out.println("Digite somente umas das opcoes!");
                            break;
                    }
                }
            }
        }
    }

    public void updateTechnicalInformations(int id) {
        for (Employee employee : developersRegistereds) {
            if (employee instanceof Developer developer) {
                if(developer.getId() == id) {

                    boolean isEdit = true;
                    while(isEdit) {
                        System.out.println("O que desejas editar?");
                        System.out.println("1 - Especialidade");
                        System.out.println("2 - Linguagens");
                        System.out.println("3 - Experiencia");
                        System.out.println("4 - Sair");
                        System.out.println("Digite a opcao correspondente: ");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                System.out.println("Especialidades disponiveis");
                                for (DeveloperSpecialty developerSpecialty : DeveloperSpecialty.values()) {
                                    System.out.println(developerSpecialty);
                                }

                                System.out.println("Digite respectivamente sua nova especialidade: ");
                                DeveloperSpecialty newSpecialty = DeveloperSpecialty.valueOf(scanner.nextLine().toUpperCase().trim());
                                developer.setDeveloperSpecialty(newSpecialty);

                                System.out.println("Quer continuar editando? Sim ou Nao");
                                String continueEdit = scanner.nextLine();
                                if (continueEdit.equalsIgnoreCase("nao")) {
                                    isEdit = false;
                                }
                                break;
                            case 2:
                                System.out.println("Linguagens disponiveis");
                                for (DeveloperLanguages developerLanguages : DeveloperLanguages.values()) {
                                    System.out.println(developerLanguages);
                                }

                                System.out.println("Quantas linguagens quer adicionar? ");
                                int languagesQuantity = scanner.nextInt();
                                scanner.nextLine();
                                DeveloperLanguages[] newLanguagesSelect = new DeveloperLanguages[languagesQuantity];

                                for (int i = 0; i < languagesQuantity; i++) {
                                    boolean valid = false;

                                    while (!valid) {
                                        System.out.print((i + 1) + "ª linguagem: ");
                                        String input = scanner.nextLine().toUpperCase().replace(" ", "_");

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
                                developer.setDeveloperLanguages(newLanguagesSelect);

                                System.out.println("Quer continuar editando? Sim ou Nao");
                                continueEdit = scanner.nextLine();
                                if (continueEdit.equalsIgnoreCase("nao")) {
                                    isEdit = false;
                                }
                                break;
                            case 3:
                                System.out.println("Experiencias disponiveis");
                                for(DeveloperExperience developerExperience : DeveloperExperience.values()) {
                                    System.out.println(developerExperience);
                                }
                                System.out.println("Digite a nova experiencia desejada");
                                DeveloperExperience newExperience = DeveloperExperience.valueOf(scanner.nextLine().toUpperCase().trim());
                                developer.setDeveloperExperience(newExperience);

                                System.out.println("Quer continuar editando? Sim ou Nao");
                                continueEdit = scanner.nextLine();
                                if (continueEdit.equalsIgnoreCase("nao")) {
                                    isEdit = false;
                                }
                                break;
                            case 4:
                                isEdit = false;
                                break;
                            default:
                                System.out.println("Digite somente umas das opcoes!");
                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void deleteDeveloper(int id) {
        int indexToRemove = -1;

        for (int i = 0; i < this.developersRegistereds.length; i++) {
            if (this.developersRegistereds[i].getId() == id) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
            return;
        }

        Employee[] temp = new Employee[this.developersRegistereds.length - 1];

        for (int i = 0, j = 0; i < this.developersRegistereds.length; i++) {
            if (i != indexToRemove) {
                temp[j++] = this.developersRegistereds[i];
            }
        }

        this.developersRegistereds = temp;

        System.out.println("Funcionário com ID " + id + " removido com sucesso!");
    }

    @Override
    public void technicalInformations(Developer developer) {
        System.out.println("Informacoes tecnicas");
        System.out.println("Nivel de experiencia: " + developer.getDeveloperExperience());
        System.out.println("Especialidade: " + developer.getSDeveloperSpecialty());
        System.out.println("Linguagens utilizadas");
        for (DeveloperLanguages developerLanguages : developer.getDeveloperLanguages()) {
            System.out.println(developerLanguages.getLanguages());
        }
    }

    @Override
    public void project(Developer developer) {
        System.out.println("Projeto atual");
        System.out.println("Titulo do projeto" + developer.getProject().getTitle());
        System.out.println("Descricao");
        System.out.println(developer.getProject().getDescription());
        if (developer.getProject().getDevelopers() != null ) {
            System.out.println("Equipe");
            for (Developer developer1 : developer.getProject().getDevelopers()) {
                System.out.println(developer1.getName());
            }
        }else {
            System.out.println("Projeto sem membros no momento");
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
