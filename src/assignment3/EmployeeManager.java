package assignment3;
import java.util.Objects;

import static assignment3.Company.truncateSalary;

public class EmployeeManager extends Employee {
    private String degree;

    public EmployeeManager(String employeeID, String employeeName, double grossSalary,String degree) throws Exception{
        super(employeeID,employeeName,grossSalary);
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }
    public void setDegree(String newDegree) throws Exception{
        if ((degree.equals("BSc") || degree.equals("MSc") || degree.equals("PhD"))) {
            this.degree = newDegree;
        } else {
            System.out.println("invalid input");
        }
    }
    @Override
    public double getGrossSalary (){ //tog bort bonus truncate variabel och returnar direkt för att minska kod.
        double bonusSalary = 0;
        double bonusBSc = 1.1;
        double bonusMSc = 1.2;
        double bonusPhD = 1.35;

        if ((Objects.equals(this.getDegree(), "BSc"))) {
            bonusSalary = (super.getGrossSalary() * bonusBSc);

        } else if ((Objects.equals(this.getDegree(), "MSc"))) {
            bonusSalary = (super.getGrossSalary() * bonusMSc);

        } else if ((Objects.equals(this.getDegree(), "PhD"))) {
            bonusSalary = (super.getGrossSalary() * bonusPhD);
        }
        return truncateSalary(bonusSalary);
    }
    @Override
    public String toString(Employee employee) {
        return this.getDegree() + ' ' + getEmployeeName() + "'s gross salary is " +
                String.format("%.2f", employee.getGrossSalary()) + " SEK per month.";

    }
}
