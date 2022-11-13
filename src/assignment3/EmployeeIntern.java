package assignment3;
public class EmployeeIntern extends Employee {
    private int GPA;

    public EmployeeIntern(String employeeID, String employeeName, double grossSalary,int GPA) throws Exception{
        super(employeeID,employeeName,grossSalary);
        this.GPA = GPA;
    }
    public int getGPA(){
        return GPA;
    }
    public void setGPA(int newGPA) throws Exception{
        this.GPA = newGPA;
    }

    @Override
    public double getGrossSalary (){
        double studentSalary = 0;
        double GPABonus = 1000;

        if (getGPA()>5 && getGPA()<=8) {
            studentSalary = super.getGrossSalary();
        } else if (getGPA()>=8 && getGPA()<=10) {
            studentSalary = super.getGrossSalary() + GPABonus;
        }
        return studentSalary;
    }

    @Override
    public double getNetSalary(Employee employee){
        return getGrossSalary();
    }
    @Override
    public String toString(Employee employee) {
        return getEmployeeName() + "'s gross salary is " + String.format("%.2f", employee.getGrossSalary()) + " SEK per month. GPA: " + this.getGPA();
    }
}
