package assignment3;

import java.util.Objects;

public class Employee implements Comparable<Employee>{
    final private String employeeID;
    private String employeeName;
    private double grossSalary;

    public Employee(String employeeID, String employeeName, double grossSalary)throws Exception{
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;
    }


    public String getEmployeeID(){return employeeID;}
    public String getEmployeeName(){
        return employeeName;
    }
    public  double getGrossSalary(){return grossSalary;}
    public double getRawGrossSalary(){return grossSalary;}

    public void setEmployeeName(String newName) throws Exception{
        this.employeeName = newName;
    }
    public void setGrossSalary(Double newGrossSalary){
        this.grossSalary = newGrossSalary;
    }

    public double getNetSalary(Employee employee) { //netSalary = grossSalary - (grossSalary * 0.1)
        double netSalary;
        double standardTaxRate = 0.9;
        netSalary = employee.getGrossSalary() * standardTaxRate;

        return netSalary;
    }
    @Override
    public boolean equals(Object anotherObject){
        if(anotherObject == this) {return true;}
        else if(anotherObject == null){ return false;}
        else if( anotherObject instanceof Employee ) {
            Employee employee = (Employee) anotherObject;
            boolean sameEmployeeID = this.employeeID.equals(employee.getEmployeeID());

            return sameEmployeeID;
        }
        else {return false;}
    }
    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
    public  String toString(Employee employee){
        return  getEmployeeName() + "'s gross salary is " +
                String.format("%.2f", employee.getGrossSalary()) + " SEK per month.";
    }

    @Override
    public int compareTo(Employee otherEmployee) {
        if(this.getGrossSalary() == otherEmployee.getGrossSalary()){return 0;}
        else if(this.getGrossSalary() > otherEmployee.getGrossSalary()){return 1;}
        else {return -1;}
    }
}
