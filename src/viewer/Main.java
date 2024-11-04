package viewer;

import controller.CompanyManagement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;
import utilities.Inputter;

public class Main {

    CompanyManagement cm = new CompanyManagement();

    public static void main(String[] args) throws Exception {
        // Menu options
        String[] options = {"Show the Employee list",
            "Add Employee", "Update Employee ",
            "Search Employee", "Save",
            "Sort Employees", "Exit"};
        Main main = new Main();

        int choice = 0;
        System.out.println(
                "Note: \nAll employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        do {
            System.out.println("\n--- Company Employee Management Program. ---");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    main.printAllEmployees();
                    break;
                case 2:
                    main.addEmployee();
                    break;
                case 3:
                    main.updateEmployee();
                    break;
                case 4:
                    main.searchEmployee();
                    break;
                case 5:
                    main.save();
                    break;
                case 6:
                    main.sort();
                    break;
                default:
                    System.out.println("Good bye!");
                    break;
            }
        } while (choice > 0 && choice < options.length);
    }

    private void addEmployee() {
        System.out.println("\n--- ADD MENU ---");
        String[] options = {"Add new Tester", "Add new Developer", "Add new Team Leader", "Return to Main menu"};

        int choice;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1: // add Tester
                    String code;
                    do {
                        code = cm.isUniqueID("Tester");
                        if (code == null || code.trim().isEmpty()) {
                            System.out.println("Invalid code. Please ensure it is unique code and non-empty.");
                        }
                    } while (code == null || code.trim().isEmpty());
                    String name;
                    do {
                        name = Inputter.inputNonBlankStr("Enter new Tester's name: ");
                        if (name == null || name.trim().isEmpty()) {
                            System.out.println("Invalid name. Please enter a non-empty name.");
                        }
                    } while (name == null || name.trim().isEmpty());

                    String baseSalInput;
                    double baseSal;
                    do {

                        baseSalInput = Inputter.inputNonBlankStr("Enter new Tester's base salary: ");
                        try {
                            baseSal = Double.parseDouble(baseSalInput);
                            if (baseSal < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            baseSal = -1;
                        }

                    } while (baseSal < 0);

                    String bonusInput;
                    double bonus;
                    do {

                        bonusInput = Inputter.inputNonBlankStr("Enter new Tester's bonus rate: ");
                        try {
                            bonus = Double.parseDouble(bonusInput);
                            if (bonus < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            bonus = -1;
                        }

                    } while (bonus < 0);

                    String type;
                    // Valid type of Tester (AM or MT)
                    do {
                        //(?i): Làm cho không phân biệt hoa thường của AM với MT
                        type = Inputter.inputNonBlankStr("Enter new Tester's type (AM or MT): ");

                        if (!type.matches("^(?i)(AM|MT)$")) {
                            System.out.println("Invalid type. Please enter AM or MT.");
                        }
                    } while (!type.matches("^(?i)(AM|MT)$"));

                    Employee newTester = new Tester(code, name, baseSal, bonus, type.toUpperCase());

                    cm.addEmployee(newTester);
                    System.out.println("--- Add Tester successfully! ---\n");
                    break;

                case 2: // add Developer 

                    do {
                        code = cm.isUniqueID("Developer");
                        if (code == null || code.trim().isEmpty()) {
                            System.out.println("Invalid code. Please ensure it is unique code and non-empty.");
                        }
                    } while (code == null || code.trim().isEmpty());

                    do {
                        name = Inputter.inputNonBlankStr("Enter new Developer's name: ");
                        if (name == null || name.trim().isEmpty()) {
                            System.out.println("Invalid name. Please enter a non-empty name.");
                        }
                    } while (name == null || name.trim().isEmpty());

                    do {
                        baseSalInput = Inputter.inputNonBlankStr("Enter new Developer's base salary: ");
                        try {
                            baseSal = Double.parseDouble(baseSalInput);
                            if (baseSal < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            baseSal = -1;
                        }

                    } while (baseSal < 0);

                    String teamName;
                    do {
                        teamName = Inputter.inputNonBlankStr("Enter team name of new Developer: ");
                        if (teamName == null || teamName.trim().isEmpty()) {
                            System.out.println("Invalid team name. Please enter a non-empty team name.");
                        }
                    } while (teamName == null || teamName.trim().isEmpty());

                    String expInput;
                    int expYear;
                    do {

                        expInput = Inputter.inputNonBlankStr("Enter new Developer's experience year: ");
                        try {
                            expYear = Integer.parseInt(expInput);
                            if (expYear < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            expYear = -1;
                        }

                    } while (expYear < 0);
                    String languages;
                    List<String> programmingLanguages;
                    do {
                        languages = Inputter.inputPattern("Enter programming languages (comma-separated): ", "([a-zA-Z0-9#\\+]+\\s*,\\s*)*[a-zA-Z0-9#\\+]+");
                        programmingLanguages = new ArrayList<>(Arrays.asList(languages.toLowerCase().split("\\s*,\\s*")));
                        if (programmingLanguages.isEmpty()) {
                            System.out.println("Invalid input. Please enter at least one programming language.");
                        }
                    } while (programmingLanguages.isEmpty());

                    Employee newDev = new Developer(code, name, baseSal, teamName, expYear, programmingLanguages);

                    cm.addEmployee(newDev);
                    System.out.println("--- Add Developer successfully! ---\n");
                    break;

                case 3:
                    // Add Team Leader
                    do {
                        code = cm.isUniqueID("Team Leader");
                        if (code == null || code.trim().isEmpty()) {
                            System.out.println("Invalid code. Please ensure it is unique code and non-empty.");
                        }
                    } while (code == null || code.trim().isEmpty());
                    do {
                        name = Inputter.inputNonBlankStr("Enter new Team Leader's name: ");
                        if (name == null || name.trim().isEmpty()) {
                            System.out.println("Invalid name. Please enter a non-empty name.");
                        }
                    } while (name == null || name.trim().isEmpty());

                    do {

                        baseSalInput = Inputter.inputNonBlankStr("Enter new Team Leader's base salary: ");
                        try {
                            baseSal = Double.parseDouble(baseSalInput);
                            if (baseSal < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            baseSal = -1;
                        }

                    } while (baseSal < 0);
                    do {
                        teamName = Inputter.inputNonBlankStr("Enter team name of the new Team Leader: ");
                        if (cm.isExitTeamLeader(teamName)) {
                            System.out.println("The team " + teamName + " already has a Team Leader. Please enter a different team.");
                        } else if (teamName == null || teamName.trim().isEmpty()) {
                            System.out.println("Invalid team name. Please enter a non-empty team name.");
                        }
                    } while (teamName == null || teamName.trim().isEmpty() || cm.isExitTeamLeader(teamName));

                    do {

                        expInput = Inputter.inputNonBlankStr("Enter new Team Leader's experience year: ");
                        try {
                            expYear = Integer.parseInt(expInput);
                            if (expYear < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            expYear = -1;
                        }

                    } while (expYear < 0);

                    do {
                        languages = Inputter.inputPattern("Enter programming languages (comma-separated): ", "([a-zA-Z0-9#\\+]+\\s*,\\s*)*[a-zA-Z0-9#\\+]+");
                        programmingLanguages = new ArrayList<>(Arrays.asList(languages.toLowerCase().split("\\s*,\\s*")));
                        if (programmingLanguages.isEmpty()) {
                            System.out.println("Invalid input. Please enter at least one programming language.");
                        }
                    } while (programmingLanguages.isEmpty());

                    do {

                        bonusInput = Inputter.inputNonBlankStr("Enter new Team Leader's bonus rate: ");
                        try {
                            bonus = Double.parseDouble(bonusInput);
                            if (bonus < 0) {
                                System.out.println("Invalid salary. Base salary must be a positive number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            bonus = -1;
                        }

                    } while (bonus < 0);
                    TeamLeader newTeamLeader = new TeamLeader(code, name, baseSal, teamName, expYear, programmingLanguages, bonus);
                    cm.addEmployee(newTeamLeader);
                    System.out.println("--- Add Team Leader successfully! ---\n");
                    break;
            }

        } while (choice > 0 && choice < options.length);

    }

    private void updateEmployee() {
        System.out.println("\n--- UPDATE MENU ---");
        String code;
        Employee emp;
        do {
            code = Inputter.inputNonBlankStr("Enter Employee ID to update: ");
            emp = cm.getEmployee(code);
            if (emp == null || code.trim().isEmpty()) {
                System.out.println("Employee does not exist. Enter again.");
            }
        } while (emp == null);

        // Show current information
        System.out.println("Current Employee Information:");
        System.out.println(cm.getEmployee(code).toFileString() + "\n");

        // update tester
        if (emp instanceof Tester) {
            Tester test = (Tester) emp;
            String name = Inputter.inputStr("Enter new name (or press Enter to keep current name): ");
            if (!name.trim().isEmpty()) {
                test.setEmpName(name.trim());
            } else {
                System.out.println("Keeping current name: " + test.getEmpName());
            }
            String baseSalInput = Inputter.inputStr("Enter new base salary (or press Enter to keep current base salary): ");
            if (!baseSalInput.trim().isEmpty()) {
                test.setBaseSal(Double.parseDouble(baseSalInput.trim()));
            } else {
                System.out.println("Keeping current base salary: " + test.getBaseSal());
            }
            String type;
            do {
                type = Inputter.inputStr("Enter new type (AM or MT) (or press Enter to keep current type): ");

                if (type.trim().isEmpty()) {
                    System.out.println("Keeping current type: " + test.getType());
                    break;
                } else if (!type.matches("^(?i)(AM|MT)$")) {
                    System.out.println("Invalid type. Please enter AM or MT.");
                } else {
                    test.setType(type.trim().toUpperCase());
                    break;
                }
            } while (true);

            String bonusInput = Inputter.inputStr("Enter new bonus rate (or press Enter to keep current base salary): ");
            if (!bonusInput.trim().isEmpty()) {
                test.setBonusRate(Double.parseDouble(bonusInput.trim()));
            } else {
                System.out.println("Keeping current bonus rate: " + test.getBonusRate());
            }

            cm.updateEmployee(test);
        }

        // update team leader
        if (emp instanceof TeamLeader) {
            TeamLeader lead = (TeamLeader) emp;

            String name = Inputter.inputStr("Enter new name (or press Enter to keep current name): ");
            if (!name.trim().isEmpty()) {
                lead.setEmpName(name.trim());
            } else {
                System.out.println("Keeping current name: " + lead.getEmpName());
            }

            String baseSalInput = Inputter.inputStr("Enter new base salary (or press Enter to keep current base salary): ");
            if (!baseSalInput.trim().isEmpty()) {
                try {
                    lead.setBaseSal(Double.parseDouble(baseSalInput.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Keeping current base salary: " + lead.getBaseSal());
                }
            } else {
                System.out.println("Keeping current base salary: " + lead.getBaseSal());
            }

            String teamName;
            do {
                teamName = Inputter.inputStr("Enter new team name (or press Enter to keep current team name): ");
                if (teamName.trim().isEmpty()) {
                    System.out.println("Keeping current team name: " + lead.getTeamName());
                    break;
                } else if (cm.isExitTeamLeader(teamName)) {
                    System.out.println("The team " + teamName + " already has a Team Leader. Please enter a different team.");
                } else {
                    lead.setTeamName(teamName.trim());
                    break;
                }
            } while (true);

            String expInput = Inputter.inputStr("Enter new experience year (or press Enter to keep current year): ");
            if (!expInput.trim().isEmpty()) {
                lead.setExpYear(Integer.parseInt(expInput.trim()));
            } else {
                System.out.println("Keeping current experience year: " + lead.getExpYear());
            }

            String bonusInput = Inputter.inputStr("Enter new bonus rate (or press Enter to keep current rate): ");
            if (!bonusInput.trim().isEmpty()) {
                lead.setBonusRate(Double.parseDouble(bonusInput.trim()));

            } else {
                System.out.println("Keeping current bonus rate: " + lead.getBonusRate());
            }

            String languages = Inputter.inputStr("Enter new programming languages (or press Enter to keep current languages): ");
            if (!languages.trim().isEmpty()) {
                if (languages.matches("([a-zA-Z0-9#\\+]+\\s*,\\s*)*[a-zA-Z0-9#\\+]+")) {
                    List<String> programmingLanguages = new ArrayList<>(Arrays.asList(languages.toLowerCase().split("\\s*,\\s*")));
                    lead.setProgrammingLanguages(programmingLanguages);
                } else {
                    System.out.println("Invalid format. Keeping current programming languages: " + lead.getProgrammingLanguages());
                }
            } else {
                System.out.println("Keeping current programming languages: " + lead.getProgrammingLanguages());
            }

            cm.updateEmployee(lead);
        }

        // update developer
        if ((emp instanceof Developer) && !(emp instanceof TeamLeader)) {
            Developer dev = (Developer) emp;
            String name = Inputter.inputStr("Enter new name (or press Enter to keep current name): ");
            if (!name.trim().isEmpty()) {
                dev.setEmpName(name.trim());
            } else {
                System.out.println("Keeping current name: " + dev.getEmpName());
            }
            String baseSalInput = Inputter.inputStr("Enter new base salary (or press Enter to keep current base salary): ");
            if (!baseSalInput.trim().isEmpty()) {
                dev.setBaseSal(Double.parseDouble(baseSalInput.trim()));
            } else {
                System.out.println("Keeping current base salary: " + dev.getBaseSal());
            }
            String teamName = Inputter.inputStr("Enter new team name (or press Enter to keep current team name): ");
            if (teamName.trim().isEmpty()) {
                System.out.println("Keeping current team name: " + dev.getTeamName());
            } else {
                dev.setTeamName(teamName.trim());
            }
            String expInput = Inputter.inputStr("Enter new exp year (or press Enter to keep current base salary): ");
            if (!expInput.trim().isEmpty()) {
                dev.setExpYear(Integer.parseInt(baseSalInput.trim()));
            } else {
                System.out.println("Keeping current exp year: " + dev.getExpYear());
            }

            String languages = Inputter.inputStr("Enter new programming languages (or press Enter to keep current languages): ");
            if (!languages.trim().isEmpty()) {

                if (languages.matches("([a-zA-Z0-9#\\+]+\\s*,\\s*)*[a-zA-Z0-9#\\+]+")) {
                    List<String> programmingLanguages = new ArrayList<>(Arrays.asList(languages.toLowerCase().split("\\s*,\\s*")));
                    dev.setProgrammingLanguages(programmingLanguages);
                } else {
                    System.out.println("Invalid format. Please enter programming languages separated by commas.");
                }
            } else {
                System.out.println("Keeping current programming languages: " + dev.getProgrammingLanguages());
            }

            cm.updateEmployee(dev);
        }

        System.out.println("--- Updated all information successfully ---");
    }

    private void searchEmployee() {
        System.out.println("\n--- SEARCH MENU ---");
        String[] options = {"Search employee by name.", "Search tester who has highest salary.", "Search developer proficent in the programming language.", "Return to Main menu."};

        int choice;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    String name = Inputter.inputNonBlankStr("Enter the name of employee that you want to search: ");
                    List<Employee> result = cm.searchByName(name);

                    if (!result.isEmpty()) {
                        System.out.println("--- Employees found ---");
                        for (Employee emp : result) {
                            System.out.println(emp);
                        }
                        System.out.println("\n");
                    } else {
                        System.out.println("No Employee is matched.\n");
                    }
                    break;
                case 2:
                    List<Tester> testerHighestSal = cm.searchTestersWithHighestSalary();
                    if (testerHighestSal == null) {
                        System.out.println("No Employee is matched.\n");
                    } else {
                        System.out.println("The tester with highest salary: ");
                        for (Employee tester : testerHighestSal) {
                            System.out.println(tester);
                        }
                        System.out.println("\n");
                    }
                    break;

                case 3:
                    String language = Inputter.inputNonBlankStr("Enter the language that you want to search developer proficent in: ");
                    List<Employee> listDev = cm.searchByPL(language);

                    if (listDev.isEmpty()) {
                        System.out.println("No Employee is matched.\n");
                    } else {
                        System.out.println("Developers with experience in " + language.toUpperCase() + ":");
                        for (Employee pL : listDev) {
                            System.out.println(pL);
                        }
                        System.out.println("\n");
                    }
                    break;
            }

        } while (choice > 0 && choice < options.length);

    }

    private void save() {
        if (cm.save()) {
            System.out.println("The data has been saved successfully!");
        } else {
            System.out.println("The data has not been saved successfully!");
        }
    }

    private void sort() {
        List<Employee> sortedList = cm.sortBySalaryAndName();

        if (sortedList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employees sorted by name and salary:");
            for (Employee emp : sortedList) {
                System.out.println(emp);
            }
        }
    }

    private void printAllEmployees(List<Employee> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No employee information yet, please add employees to show the information.\n");
        }
        for (Employee emp : list) {
            System.out.println(emp.toFileString());
        }
    }

    private void printAllEmployees() {
        printAllEmployees(cm.getAllEmployees());

    }
}
