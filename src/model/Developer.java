package model;

import java.util.List;

/**
 *
 * @author phamnguyenhoanganh
 */
public class Developer extends Employee {

    private String teamName;
    private List<String> programmingLanguages;
    private int expYear;

    public Developer(String empID, String empName, double baseSal, String teamName, int expYear, List<String> programmingLanguages) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    @Override
    public double getSalary() {
        if (expYear >= 3 && expYear < 5) {
            return (getBaseSal() + expYear * 1000000);
        } else if (expYear >= 5) {
            return (getBaseSal() + expYear * 2000000);
        } else {
            return getBaseSal();
        }

    }

    @Override
    public String toString() {
        return super.toString() + "_" + teamName + "_" + expYear;
    }
    
    @Override
    public String toFileString() {
        return String.format("%s_%s", toString(), programmingLanguages);
    }

}
