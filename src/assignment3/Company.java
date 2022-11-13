package assignment3;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Company{
    private final DataExceptionLogic dataExceptionLogic = new DataExceptionLogic();

    private ArrayList<Employee> Employees;

    public Company() {
        this.Employees = new ArrayList<>();
    }


    public String createEmployee(String employeeID, String name, double grossSalary) throws Exception { // reg employee
        checkReapetedIDException(employeeID);
        dataExceptionLogic.checkIfEmployeeCanBeCreated(employeeID, name, grossSalary);
        Employees.add(new Employee(employeeID, name, truncateSalary(grossSalary)));

        return "Employee " + employeeID + " was registered successfully.";
    }
    public String createEmployee(String employeeID, String name, double grossSalary, String degree) throws Exception { // employee manager
        dataExceptionLogic.checkIfEmployeeCanBeCreated(employeeID, name, grossSalary);
        dataExceptionLogic.checkIfDegreeIsAllowed(degree);
        Employees.add(new EmployeeManager(employeeID, name, truncateSalary(grossSalary), degree));

        return "Employee " + employeeID + " was registered successfully.";
    }
    public String createEmployee(String employeeID, String name, double grossSalary, String degree, String department)throws Exception{ // employee director
        dataExceptionLogic.checkIfEmployeeCanBeCreated(employeeID, name, grossSalary);
        dataExceptionLogic.checkIfDegreeIsAllowed(degree);
        dataExceptionLogic.checkIfDepartmentIsAllowed(department);
        Employees.add(new EmployeeDirector(employeeID, name, truncateSalary(grossSalary), degree, department));

        return "Employee " + employeeID + " was registered successfully.";
        }
    public String createEmployee(String employeeID, String name, double grossSalary, int GPA) throws Exception{ //employee intern
        dataExceptionLogic.checkIfEmployeeCanBeCreated(employeeID, name, grossSalary);
        dataExceptionLogic.checkIfGPAIsAllowed(GPA);
        Employees.add(new EmployeeIntern(employeeID, name, truncateSalary(grossSalary), GPA));

        return "Employee " + employeeID + " was registered successfully.";
    }

    public String printEmployee(String employeeID) throws Exception { //prints Employee
        Employee employee = getEmployee(employeeID);
        return employee.toString(employee);
    }

    public static double truncateSalary(double truncDouble){ // truncates the salary
        DecimalFormat salaryTruncator = new DecimalFormat("##.00");
        salaryTruncator.setRoundingMode(RoundingMode.DOWN);

        return Double.parseDouble(salaryTruncator.format(truncDouble));
    }
    public double getNetSalary(String employeeID) throws Exception { // prints truncated employee net salary
        Employee employee = getEmployee(employeeID);
        double employeeNetSalary = employee.getNetSalary(employee);

        return truncateSalary(employeeNetSalary);
    }
    public Employee getEmployee(String employeeID) throws Exception{ //finds an employee from employeeID and returns the employee
        for (Employee employee : Employees) {
            if (employeeID.equals(employee.getEmployeeID())) {
                return employee;
            }
        }
        throw new EmployeeEmptyException("Employee IDx was not registered yet.");
    }
    public String removeEmployee(String employeeID)throws Exception { //removes an employee
        if (getEmployee(employeeID) != null) {
            for (int i = 0; i < Employees.size(); i++) {
                if (employeeID.equals(Employees.get(i).getEmployeeID())) {
                    Employees.remove(i);
                    return "Employee " + employeeID + " was successfully removed.";
                }
            }
        }
        return null;
    }
    public String printAllEmployees() throws Exception { //prints all employees in Employees arraylist
            String allHiredList = "All registered employees:\n";
            if (Employees.isEmpty()) {
                throw new EmployeeEmptyException("No employees registered yet.");
            } else {
                for (Employee employee : Employees) {
                    allHiredList += (employee.toString(employee)) + "\n";
                }
            }
        return allHiredList;
    }
    public double getTotalNetSalary(){ // returns combined net salary of all employees in Employees arraylist
        double totalNetSalary = 0;
        if (Employees.isEmpty()){
            throw new EmployeeEmptyException("No employees registered yet.");
        }else {
            for (Employee employee : Employees) {
                totalNetSalary = totalNetSalary + employee.getNetSalary(employee);
            }
        }
        return truncateSalary(totalNetSalary);
    }
    public String printSortedEmployees(){ //prints employees in Employee arraylist sorted by gross salary
        if (Employees.isEmpty()){
            throw new EmployeeEmptyException("No employees registered yet.");
        } else {
            Collections.sort(Employees);
            String sortedEmployees = "Employees sorted by gross salary (ascending order):\n";
            for (Employee employee : Employees) {
                sortedEmployees += (employee.toString(employee)) + "\n";
            }
            return sortedEmployees;
        }
    }
    public String updateEmployeeName(String employeeID, String newName)throws Exception{
        dataExceptionLogic.checkIfNameIsAllowed(newName);
        Employee employee = getEmployee(employeeID);
        employee.setEmployeeName(newName);
        return "Employee " + employeeID + " was updated successfully";
    }
    public String updateInternGPA(String employeeID, int newGPA) throws Exception {
        dataExceptionLogic.checkIfGPAIsAllowed(newGPA);
        Employee employee = getEmployee(employeeID);
        ((EmployeeIntern) employee).setGPA(newGPA);
        return "Employee " + employeeID + " was updated successfully";
    }
    public String updateManagerDegree(String employeeID, String newDegree)throws Exception{
        dataExceptionLogic.checkIfDegreeIsAllowed(newDegree);
        Employee employee = getEmployee(employeeID);
        ((EmployeeManager) employee).setDegree(newDegree);

        return "Employee " + employeeID + " was updated successfully";
    }
    public String updateDirectorDept(String employeeID, String newDepartment) throws Exception{
        dataExceptionLogic.checkIfDepartmentIsAllowed(newDepartment);
        Employee employee = getEmployee(employeeID);
        ((EmployeeDirector) employee).setDepartment(newDepartment);

        return "Employee " + employeeID + " was updated successfully";
    }
    public String updateGrossSalary(String employeeID, double newGrossSalary) throws Exception{
        dataExceptionLogic.checkIfSalaryIsAllowed(newGrossSalary);
        Employee employee = getEmployee(employeeID);
        employee.setGrossSalary(newGrossSalary);
        return "Employee " + employeeID + " was updated successfully";
    }
   public HashMap<String, Integer> mapEachDegree(){
        int numOfBSc = 0;
        int numOfMSc = 0;
        int numOfPhD = 0;
        HashMap<String, Integer> allDegrees = new HashMap<>();

       if (Employees.isEmpty()){
           throw new EmployeeEmptyException();
       }else
           for (Employee employee : Employees) {
            if(employee instanceof EmployeeManager) {
                if (Objects.equals(((EmployeeManager) employee).getDegree(), "BSc")) {
                    numOfBSc++;
                    allDegrees.put("BSc",numOfBSc);
                } else if (Objects.equals(((EmployeeManager) employee).getDegree(), "MSc")) {
                    numOfMSc++;
                    allDegrees.put("MSc",numOfMSc);
                } else if (Objects.equals(((EmployeeManager) employee).getDegree(), "PhD")) {
                    numOfPhD++;
                    allDegrees.put("PhD",numOfPhD);
                }
            }
        }
        return allDegrees;
    }
    public String promoteToManager(String employeeID, String newDegree) throws Exception {
        double grossSalary;
        String name;

        grossSalary = getEmployee(employeeID).getRawGrossSalary();
        name = getEmployee(employeeID).getEmployeeName();
        removeEmployee(employeeID);
        Employees.add(new EmployeeManager(employeeID, name, grossSalary, newDegree));

        return employeeID + " promoted successfully to Manager.";
    }
    public String promoteToDirector(String employeeID, String newDegree, String newDepartment) throws Exception {
        double grossSalary;
        String name;

        grossSalary = getEmployee(employeeID).getRawGrossSalary();
        name = getEmployee(employeeID).getEmployeeName();
        removeEmployee(employeeID);
        Employees.add(new EmployeeDirector(employeeID, name, grossSalary, newDegree, newDepartment));

        return employeeID + " promoted successfully to Director.";
    }
    public String promoteToIntern(String employeeID, int newGPA) throws Exception {
        double grossSalary;
        String name;

        grossSalary = getEmployee(employeeID).getRawGrossSalary();
        name = getEmployee(employeeID).getEmployeeName();
        removeEmployee(employeeID);
        Employees.add(new EmployeeIntern(employeeID, name, grossSalary, newGPA));

        return employeeID + " promoted successfully to Intern.";
    }
    public void checkReapetedIDException(String employeeID) throws Exception{ // this method checks if employeeID already exists
        for(Employee employee:Employees){
            if(Objects.equals(employee.getEmployeeID(), employeeID)){
                throw new RepeatedIDException("Cannot register. ID " + employeeID + " is already registered.");
            }
        }
    }

}


