package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;

/**
 *
 * @author phamnguyenhoanganh
 */
public class EmployeeFileText implements IFileReadWrite<Employee> {

    private final String FILE_NAME = "src/fileio/Employee.txt"; 
    
    @Override
    public List<Employee> read() throws Exception {

       List<Employee> empList = new ArrayList<>();
        File f;
        FileInputStream file;
        BufferedReader myInput; //create Buffer
        try {
            f = new File(FILE_NAME); //open file
            String fullPath = f.getAbsolutePath();
            file = new FileInputStream(fullPath);
            myInput = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = myInput.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String split[] = line.split("_");
                switch(split.length) {
                    case 6: // Tester: code_name_baseSal_bonus_type
                        String code = split[1].trim();
                        String name = split[2].trim();
                        double baseSal = Double.parseDouble(split[3].trim());
                        double bonus = Double.parseDouble(split[4].trim());
                         String type = split[5].trim();
                        empList.add(new Tester(code, name, baseSal, bonus, type));
                    break;
                    case 7: // Developer: code_name_baseSal_teamName_exp_program
                        code = split[1].trim();
                        name = split[2].trim();
                        baseSal = Double.parseDouble(split[3].trim());
                        String teamName = split[4].trim();
                        int expYear = Integer.parseInt(split[5].trim());
                        String pl = split[6].trim().substring(1, split[6].trim().length() -1 ); // bo hai dau ngoac dau va cuoi
                        List <String> listPl = new ArrayList<>();
                        for (String s : pl.split(",")) {
                            listPl.add(s.trim());
                        }
                        empList.add(new Developer(code, name, baseSal, teamName, expYear, listPl));
                    break;
                    case 8: // TeamLeader: code_name_baseSal_teamName_exp_program_bonus
                        code = split[1].trim();
                        name = split[2].trim();
                        baseSal = Double.parseDouble(split[3].trim());
                        teamName = split[4].trim();
                        expYear = Integer.parseInt(split[5].trim());
                        pl = split[6].trim().substring(1, split[6].trim().length() -1 ); // bo hai dau ngoac dau va cuoi
                        listPl = new ArrayList<>();
                        for (String s : pl.split(",")) {
                            listPl.add(s.trim());
                        }
                        bonus = Double.parseDouble(split[7].trim());
                        empList.add(new TeamLeader(code, name, baseSal, teamName, expYear, listPl, bonus));
                    break;
                    default:
                    break;
                }
            }

            myInput.close();

        } catch (Exception ex) {
            throw ex;
        }

        return empList;


    }

    @Override
    public boolean write(List<Employee> empList) throws Exception {
        if (empList == null) {
            return false;
        }
        File f;
        FileOutputStream file;
        BufferedWriter myOutput; //create Buffer
        try {
            f = new File(FILE_NAME); //open file
            String fullPath = f.getAbsolutePath();
            file = new FileOutputStream(fullPath);
            myOutput = new BufferedWriter(new OutputStreamWriter(file));
            int i = 1;

            for (Employee emp : empList) {
                if (i > 0) {
                    myOutput.newLine();
                }

                myOutput.write((i++) + "_" + emp.toFileString());
            }

            myOutput.close();

        } catch (Exception ex) {
            throw ex;
        }

        return true;

    }

}
