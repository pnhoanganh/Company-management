
package model;
import java.util.List;

/**
 *
 * @author phamnguyenhoanganh
 */
public class TeamLeader extends Developer {
    private double bonusRate;

    public TeamLeader(String empID, String empName, double baseSal, String teamName, int expYear, List<String> programmingLanguages, double bonusRate) {
        super(empID, empName, baseSal, teamName, expYear, programmingLanguages);
        this.bonusRate = bonusRate;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }
    
    @Override
    public double getSalary() {
        double salaryOfDev = super.getSalary();
        return (salaryOfDev + bonusRate*salaryOfDev);
        
    }

    @Override
    public String toFileString() {
        return String.format("%s_%.2f", super.toFileString(), bonusRate);
    }
    
    
}