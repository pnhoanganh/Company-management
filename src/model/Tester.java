
package model;

/**
 *
 * @author phamnguyenhoanganh
 */
public class Tester extends Employee {
    private double bonusRate;
    private String type;

    public Tester(String empID, String empName, double baseSal, double bonusRate, String type) {
        super(empID, empName, baseSal);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public String getType() {
        return type;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    @Override
    public double getSalary() {
        return (getBaseSal() + bonusRate*(getBaseSal()));
    }
    
    @Override
    public String toFileString() {
         return String.format("%s_%.2f_%s", super.toFileString() , bonusRate, type);
    }
    
    
}
