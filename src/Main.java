import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Person;
import com.devdojo.domain.Project;
import com.devdojo.domain.enums.Developer.DeveloperExperience;
import com.devdojo.domain.enums.Developer.DeveloperLanguages;
import com.devdojo.domain.enums.Developer.DeveloperSpecialty;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.impl.DeveloperServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EmployeeBenefits[] employeeBenefits = new EmployeeBenefits[]{EmployeeBenefits.DENTAL_PLAN, EmployeeBenefits.FOOD_VOUCHER};
        DeveloperLanguages[] developerLanguages = new DeveloperLanguages[]{DeveloperLanguages.CSHARP, DeveloperLanguages.JAVA};
        Employee developer = new Developer(12, "EU", "2334455", 34, Person.Sex.MASCULINO, "pmabesiji@gmail", "234567777", 1235465.89, employeeBenefits, DeveloperSpecialty.BACKEND, developerLanguages, DeveloperExperience.PLENO);

        Developer[] developers = new Developer[]{(Developer) developer};
        Project project = new Project("Titulo 1", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", developers, "ontem", "hoje");

        ((Developer) developer).setProject(project);

        DeveloperServiceImpl developerService = new DeveloperServiceImpl();

        developerService.technicalInformations((Developer) developer);
        developerService.project((Developer) developer);
    }
}