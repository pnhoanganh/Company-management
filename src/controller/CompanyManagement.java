package controller;

import fileio.EmployeeFileText;
import fileio.IFileReadWrite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;
import utilities.Inputter;

public class CompanyManagement {

    private List<Employee> empList;

    public CompanyManagement() {
        empList = load();
        if (empList == null) {
            empList = new ArrayList<>();
        }
    }

    private List<Employee> load() {
        try {
            IFileReadWrite file = new EmployeeFileText();
            return file.read();
        } catch (Exception ex) {
            return null;
        }

    }

    public boolean save() {
        try {
            IFileReadWrite file = new EmployeeFileText();
            return file.write(empList);
        } catch (Exception ex) {
            return false;
        }

    }

    public List<Employee> getAllEmployees() {
        if (empList == null) {
            return null;
        }

        return empList;
    }

    // Thêm một đối tượng Employee (nhân viên) vào danh sách empList.
    public boolean addEmployee(Employee emp) {
        if (emp == null) {
            return false;
        }

        return empList.add(emp);
    }

    public Employee getEmployee(String code) {
        if (code.trim().isEmpty()) {
            return null;
        }
        for (Employee emp : empList) {
            if (emp.getEmpID().equalsIgnoreCase(code)) {
                return emp;
            }
        }
        return null;
    }

    public boolean isExistCode(String code) {

        return getEmployee(code) != null;
    }

    // Check Employee ID có bị trùng hay không
    public String isUniqueID(String role) {
        String code;
        boolean check;
        do {
            code = Inputter.inputNonBlankStr("Enter new " + role + " ID: ");
            check = isExistCode(code);
            if (check) {
                System.out.println("Employee ID has exist, please re-enter.");
            }
        } while (check);
        return code;
    }

    public boolean updateEmployee(Employee updateEmp) {
        int index = empList.indexOf(updateEmp);
        if (index == -1) {
            return false;
        }

        empList.set(index, updateEmp);
        return true;

    }

    public List<Employee> searchByName(String name) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : empList) {
            if (emp.getEmpName().toLowerCase().contains(name.toLowerCase())) {
                list.add(emp);
            }
        }
        return list;
    }

    public List<Tester> searchTestersWithHighestSalary() {
        List<Tester> testersWithHighestSalary = new ArrayList<>();
        double highestSal = 0;

        for (Employee emp : empList) {
            if (!(emp instanceof Tester)) {
                continue;
            }
            
            Tester tester = (Tester) emp;
            if (tester.getSalary() > highestSal) {
                highestSal = tester.getSalary();
                testersWithHighestSalary.clear();
                testersWithHighestSalary.add(tester);
            } else if (tester.getSalary() == highestSal) {
                testersWithHighestSalary.add(tester);
            }
        }

        return testersWithHighestSalary;
    }

    public List<Employee> searchByPL(String pl) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : empList) {
            if (!(emp instanceof Developer)) {
                continue;
            }
            Developer dev = (Developer) emp;
            if (dev.getProgrammingLanguages().contains(pl.toLowerCase())) {
                list.add(dev);
            }
//            for (int i = 0; i < dev.getProgrammingLanguages().size(); i++) {
//                if(dev.getProgrammingLanguages().get(i).contains(pl.toLowerCase())){
//                     list.add(dev);
//                }
//            }
                
        }
        return list;

    }

    // Phương thức kiểm tra team đã có Team Leader hay chưa
    public boolean isExitTeamLeader(String teamName) {
        for (Employee emp : empList) {
            if (!(emp instanceof TeamLeader)) {
                continue;
            }

            TeamLeader lead = (TeamLeader) emp;
            if (lead.getTeamName().equalsIgnoreCase(teamName)) {
                return true;
            }
        }
        return false;

    }

    public List<Employee> sortBySalaryAndName() {
        List<Employee> list = new ArrayList<>(this.empList);
        Comparator comp = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int result = o1.getEmpName().compareTo(o2.getEmpName());
                if (result == 0) {
                    result = Double.compare(o1.getSalary(), o2.getSalary());
                }
                return result;
            }

        };
        Collections.sort(list, comp);
        return list;

    }

}
