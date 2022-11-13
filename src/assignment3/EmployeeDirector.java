package assignment3;
public class EmployeeDirector extends EmployeeManager {
    private String department;
    public EmployeeDirector(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception{
        super(employeeID, employeeName, grossSalary, degree);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }
    public void setDepartment(String newDepartment) throws Exception{
        this.department = newDepartment;
    }


   @Override
   public double getGrossSalary() {
       double departmentBonus = 5000;
       return super.getGrossSalary() + departmentBonus;
   }

   @Override
    public double getNetSalary(Employee employee) {
       double netSalary = 0;
       double highIncomeCutOff = 50000;
       double lowIncomeCutOff =  30000;
       double lowIncomeTAX = 0.9; // 10%
       double middleIncomeTAX = 0.8; // 20%
       double highIncomeTAX = 0.6; // 40%

       if(employee.getGrossSalary()<=lowIncomeCutOff){
           netSalary = getGrossSalary() * lowIncomeTAX;
       }
       else if(employee.getGrossSalary()>=lowIncomeCutOff && employee.getGrossSalary()<=highIncomeCutOff){
           netSalary = getGrossSalary() * middleIncomeTAX;
       }
       else if (employee.getGrossSalary()>highIncomeCutOff) {
            netSalary = (lowIncomeCutOff * middleIncomeTAX) + (getGrossSalary() - lowIncomeCutOff) * highIncomeTAX;
       }

       return netSalary;
   }
    @Override
    public String toString(Employee employee) {
        return this.getDegree() + ' ' + getEmployeeName() + "'s gross salary is " +
                String.format("%.2f", employee.getGrossSalary()) + " SEK per month. Dept: " + this.getDepartment();
    }
}

