
package model;

import java.util.Objects;

/**
 *
 * @author phamnguyenhoanganh
 */
public abstract class Employee implements ITextFileOutput {
    private String empID;
    private String empName;
    private double baseSal;

    public Employee(String empID, String empName, double baseSal) {
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public double getBaseSal() {
        return baseSal;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setBaseSal(double baseSal) {
        this.baseSal = baseSal;
    }
    
    public abstract double getSalary();

    @Override
    public String toString() {
        return empID + "_" + empName + "_" + baseSal;
    }
    
    //Insert code => equals()
   @Override
    public boolean equals(Object obj) {
        // Kiểm tra xem đối tượng hiện tại (this) và đối tượng truyền vào (obj) có bằng nhau không
        if (this == obj) {
            return true;  // Nếu bằng thì hai đối tượng là giống nhau
        }

        // Kiểm tra xem đối tượng (obj) có phải là null hay không
        if (obj == null) {
            return false;  // Nếu obj là null thì không thể so sánh
        }

        // Kiểm tra xem hai đối tượng có cùng kiểu dữ liệu (cùng class) không
        if (getClass() != obj.getClass()) {
            return false;  // Nếu không cùng class thì không bằng nhau
        }

        // Ép kiểu đối tượng obj về kiểu Employee để có thể so sánh thuộc tính của nó
        final Employee other = (Employee) obj;

        // So sánh thuộc tính empID của hai đối tượng hiện tại (this) và đối tượng truyền vào (obj)
        if(!Objects.equals(this.empID, other.empID)) {
            return false;  // Nếu empID không giống nhau thì trả về false
        }

        return true;  // Nếu qua được tất cả các kiểm tra trên, hai đối tượng là bằng nhau
    }
    
    @Override 
    public String toFileString() {
        return this.toString();
    }
    
}
